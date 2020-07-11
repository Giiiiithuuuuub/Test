package com.atguigu.spark.streaming.requirement2.serv

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
        
        val changeDS: DStream[clickLog] = initDS.map(
            x => {
                val strings: Array[String] = x.split(" ")
                clickLog(strings(0), strings(1), strings(2), strings(3), strings(4))
            }
        )
        
        val sdf: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
        val value: DStream[((String, String,String, String), Int)] = changeDS.map(
            y => {
                ((sdf.format(new Date(y.ts.toLong)), y.area, y.city,y.adid), 1)
            }
        ).reduceByKey(_ + _)
        
        value.foreachRDD(
            x=>x.foreachPartition(
                y =>{
                    val connection: Connection = MyJDBCUtil.getConnection
    
                    val statement: PreparedStatement = connection.prepareStatement(
                        """
                          |insert into area_city_ad_count (dt,area,city,adid,count)
                          |values(?,?,?,?,?)
                          |on duplicate key
                          |update count = count + ?
                          |""".stripMargin)
    
    
                    y.foreach {
                        case ((dt, area, city, adid), count) => {
    
                            statement.setString(1,dt)
                            statement.setString(2,area)
                            statement.setString(3,city)
                            statement.setString(4,adid)
                            statement.setLong(5,count)
                            statement.setLong(6,count)
                            statement.executeUpdate()
                        }
    
                    }
                    
                    statement.close()
                    connection.close()
                }
            )
        )
        
        
    }
}
