package com.atguigu.spark.streaming.requirement.service

import java.sql.{Connection, PreparedStatement}
import java.text.SimpleDateFormat
import java.util.Date

import com.atguigu.spark.streaming.requirement.DAO.RequirementTwoDAO
import com.atguigu.spark.streaming.requirement.clickLog
import com.atguigu.summer.core.Service
import com.atguigu.summer.utils.JDBCUtils
import org.apache.spark.streaming.dstream.DStream

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-17 14:16
 */
class RequirementTwoService extends Service{
    
    
    private val reqTwoDAO = new RequirementTwoDAO
    
    override def analysis()={
        val ds: DStream[String] = reqTwoDAO.readFromKafka()
        val logDS = ds.map(
            data => {
                val datas = data.split(" ")
                clickLog( datas(0),datas(1),datas(2),datas(3),datas(4) )
            }
        )
        
        val sdf = new SimpleDateFormat("yyyy-MM-dd")
    
        val result: DStream[((String, String, String, String), Int)] = logDS
            .map(
                log => {
                    ((sdf.format(new Date(log.ts.toLong)), log.area, log.city, log.adId), 1)
                }
            )
            .reduceByKey(_ + _)
        
        result.foreachRDD(
            rdd =>{
                rdd.foreachPartition(
                    datas =>{
                        val connection: Connection = JDBCUtils.getConnection
    
                        val statement: PreparedStatement = connection.prepareStatement(
                            """
                              |insert into area_city_ad_count (dt,area,city,adid,count)
                              |values(?,?,?,?,?)
                              |on duplicate key
                              |update count = count + ?
                              |""".stripMargin)
                        
                        datas.foreach{
                             
    
                                case((dt,area,city,adid),count)=>{
    
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
            }
        )
    }
    
}
