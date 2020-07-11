package com.atguigu.spark.streaming.requirement2.serv

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
        
        //从kafka获取数据
        val tempData: DStream[String] = reqOneDAO.getFromKafka("requirement.properties")
        
        //包装为样例类
        
        val temp: DStream[clickLog] = tempData.map(
            x => {
                val datas: Array[String] = x.split(" ")
                clickLog(datas(0), datas(1), datas(2), datas(3), datas(4))
            }
        )
        
        val AfterFilter: DStream[((String, String, String), Int)] = temp.transform(
            x => {
                
                val connection: Connection = MyJDBCUtil.getConnection
                //周期性的判断是否在黑名单中
                
                val statement: PreparedStatement = connection.prepareStatement(
                    """
                      |select userid from black_list
                      |""".stripMargin)
                val resultSet: ResultSet = statement.executeQuery()
                //黑名单集合
                val blackList: ListBuffer[String] = ListBuffer[String]()
                //将黑名单添加到集合中，注意是使用if循环
                if (resultSet.next()) {
                    blackList.append(resultSet.getString(1))
                }
                
                resultSet.close()
                statement.close()
                connection.close()
                
                //如果在黑名单中，则过滤
                val filterRDD: RDD[clickLog] = x.filter(
                    y => {
                        !blackList.contains(y.userid)
                    }
                )
                
                val sdf: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
                
                //将过滤后的数据进行格式的变换,聚合
                filterRDD.map(
                    x => {
                        ((sdf.format(new Date(x.ts.toLong)), x.userid, x.adid), 1)
                    }
                ).reduceByKey(_ + _)
                
                
            }
        )
        
        
        AfterFilter.foreachRDD(
            x => {
                x.foreachPartition {
                    
                    datas => {
                        
                        val connection: Connection = MyJDBCUtil.getConnection
                        
                        //如果不在黑明单中，添加数据到用户表中
                        
                        val statement: PreparedStatement = connection.prepareStatement(
                            """
                              |insert into user_ad_count (dt,userid,adid,count)
                              |values(?,?,?,?)
                              |on duplicate key
                              |update count = count + ?
                              |""".stripMargin)
                        
                        
                        //查看数据是否超过阈值,如果超过加入黑名单，如果没超过，则更新
                        val statement2: PreparedStatement = connection.prepareStatement(
                            """
                              |insert into black_list (userid)
                              |select userid from user_ad_count
                              |where userid = ? and count >= 100
                              |on duplicate key
                              |update userid = ?
                              |""".stripMargin)
                        
                        datas.foreach {
                            case ((ts, userid, adid), count) => {
                                
                                statement.setString(1, ts)
                                statement.setString(2, userid)
                                statement.setString(3, adid)
                                statement.setLong(4, count)
                                statement.setLong(5, count)
                                statement.executeUpdate()
                                
                                statement2.setString(1, userid)
                                statement2.setString(2, userid)
                                statement2.executeUpdate()
                            }
                        }
                        
                        statement.close()
                        statement2.close()
                        connection.close()
                        
                    }
                    
                }
            }
        )
        
        
        tempData.print()
    }
}
