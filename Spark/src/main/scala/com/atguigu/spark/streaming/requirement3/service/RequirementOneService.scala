package com.atguigu.spark.streaming.requirement3.service

import java.sql.{Connection, PreparedStatement, ResultSet}
import java.text.SimpleDateFormat
import java.util.Date

import com.atguigu.spark.streaming.requirement2.clickLog
import com.atguigu.spark.streaming.requirement2.dao.RequirementOneDAO
import core.SummerService
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.DStream
import utils.MyJDBCUtil

import scala.collection.mutable.ListBuffer

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-17 20:48
 */
class RequirementOneService extends SummerService {
    private val reqOneDAO = new RequirementOneDAO
    
    override def analysis() = {
        val initDS: DStream[String] = reqOneDAO.getFromKafka("requirement.properties")
        
        val changeDS: DStream[clickLog] = initDS.map(
            x => {
                val datas: Array[String] = x.split(" ")
                clickLog(datas(0), datas(1), datas(2), datas(3), datas(4))
            }
        )
        
        //周期性的遍历黑名单,        如果黑名单中有，则过滤
        val filterDS: DStream[((String, String, String), Int)] = changeDS.transform {
            x => {
                val connection: Connection = MyJDBCUtil.getConnection
                val statement: PreparedStatement = connection.prepareStatement(
                    """
                      |select userid from black_list
                      |""".stripMargin)
                val rs: ResultSet = statement.executeQuery()
            
                val blackList: ListBuffer[String] = ListBuffer[String]()
            
                if (rs.next()) {
                    blackList.append(rs.getString(1))
                }
            
                rs.close()
                statement.close()
                connection.close()
            
                val sdf: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
                x.filter(
                    y => {
                        !blackList.contains(y.userid)
                    }
                )
                    .map(
                        z => {
                            ((sdf.format(new Date(z.ts.toLong)), z.userid, z.adid), 1)
                        }
                    )
                    .reduceByKey(_ + _)
            
            }
        }
        
        
        
        //过滤后的数据加入到用户表中
        filterDS.foreachRDD(
            x => x.foreachPartition(
                data => {
                    val connection: Connection = MyJDBCUtil.getConnection
    
                    val statement: PreparedStatement = connection.prepareStatement(
                        """
                          |insert into user_ad_count (dt,userid,adid,count)
                          |values (?,?,?,?)
                          |on duplicate key
                          |update count = count + ?
                          |""".stripMargin)
                    
                    val statement2 : PreparedStatement = connection.prepareStatement(
                        """
                          |insert into black_list (userid)
                          |select userid from user_ad_count
                          |where userid = ? and count >= 100
                          |on duplicate key
                          |update userid = ?
                          |""".stripMargin)
                    
                    data.foreach(
                        x => {
                            statement.setString(1,x._1._1)
                            statement.setString(2,x._1._2)
                            statement.setString(3,x._1._3)
                            statement.setLong(4,x._2)
                            statement.setLong(5,x._2)
                            statement.executeUpdate()
                            
                            statement2.setString(1,x._1._2)
                            statement2.setString(2,x._1._2)
                            statement2.executeUpdate()
                        }
    
                    )
                    
                    statement.close()
                    statement2.close()
                    connection.close()
                }
            )
        )
        
        initDS.print()
        
    }
}
