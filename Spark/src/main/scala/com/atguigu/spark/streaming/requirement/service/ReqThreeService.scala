package com.atguigu.spark.streaming.requirement.service

import com.atguigu.spark.streaming.requirement.DAO.ReqThreeDAO
import com.atguigu.spark.streaming.requirement.clickLog
import com.atguigu.summer.core.Service
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.dstream.DStream

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-17 15:49
 */
class ReqThreeService extends Service {
    
    private val reqThreeDAO = new ReqThreeDAO
    
    override def analysis() = {
        val ds: DStream[String] = reqThreeDAO.readFromKafka()
        
        val logDS = ds.map(
            data => {
                val datas = data.split(" ")
                clickLog(datas(0), datas(1), datas(2), datas(3), datas(4))
            }
        )
        
        logDS
            .map(
                x => {
                    val ts = x.ts.toLong
                    
                    ((x.adId, ts / 10000 * 10000), 1)
                }
            )
            .reduceByKeyAndWindow((a : Int,b : Int) => a + b,Seconds(3),Seconds(3))
            .map{
                case((adid,time),sum)=>{
                    (adid,(time,sum))
                }
            }
            .groupByKey()
            .mapValues(
                x =>{
                    x.toList.sortWith(
                        (left,right)=>{
                            left._1 < right._1
                        }
                    )
                }
            )
            .print()
    }
}
