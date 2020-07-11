package com.atguigu.spark.streaming.requirement3.service

import java.sql.{Connection, PreparedStatement}
import java.text.SimpleDateFormat
import java.util.Date

import com.atguigu.spark.streaming.requirement2.clickLog
import com.atguigu.spark.streaming.requirement2.dao.RequirementTwoDAO
import core.SummerService
import org.apache.spark.streaming.dstream.DStream
import utils.MyJDBCUtil

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-18 0:59
 */
class RequirementTwoService extends SummerService {
    private val reqTwoDAO = new RequirementTwoDAO
    
    override def analysis(): Any = {
        val initDS: DStream[String] = reqTwoDAO.getFromKafka("requirement.properties")
        
        val sdf: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
        val resultDS: DStream[((String, String, String, String), Int)] = initDS.map(
            x => {
                val strings: Array[String] = x.split(" ")
                
                ((sdf.format(strings(0).toLong), strings(1), strings(2), strings(4)), 1)
            }
        )
            .reduceByKey(_ + _)
        
        resultDS.foreachRDD(
            x => x.foreachPartition {
                data => {
                    val connection: Connection = MyJDBCUtil.getConnection
                    
                    val statement: PreparedStatement = connection.prepareStatement(
                        """
                          |insert into area_city_ad_count (dt,area,city,adid,count)
                          |values (?,?,?,?,?)
                          |on duplicate key
                          |update count = count + ?
                          |""".stripMargin)
                    
                    data.foreach{
                        case((day,area,city,adid),sum)=>{
                            statement.setString(1,day)
                            statement.setString(2,area)
                            statement.setString(3,city)
                            statement.setString(4,adid)
                            statement.setLong(5,sum)
                            statement.setLong(6,sum)
                            
                            statement.executeUpdate()
                        }
                    }
                    
                    statement.close()
                    connection.close()
                }
            }
        )
        
        
    }
}
