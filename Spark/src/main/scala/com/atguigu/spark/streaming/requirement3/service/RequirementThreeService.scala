package com.atguigu.spark.streaming.requirement3.service

import com.atguigu.spark.streaming.requirement2.clickLog
import com.atguigu.spark.streaming.requirement2.dao.RequirementThreeDAO
import core.SummerService
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.dstream.DStream

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-18 1:33
 */
class RequirementThreeService extends SummerService {
    private val reqThreeDAO = new RequirementThreeDAO
    
    override def analysis(): Any = {
        val initDS: DStream[String] = reqThreeDAO.getFromKafka("requirement.properties")
        val changeDS: DStream[clickLog] = initDS.map(
            x => {
                val strings: Array[String] = x.split(" ")
                
                clickLog(strings(0),
                    strings(1),
                    strings(2),
                    strings(3),
                    strings(4))
            }
        )
        
        changeDS
            .map(
                x => {
                    ((x.adid, x.ts.toLong/10000 * 10000), 1)
                }
            )
            .reduceByKeyAndWindow((x: Int, y: Int) => x + y, Seconds(3), Seconds(3))
            .map(
                x => {
                    (x._1._1, (x._1._2, x._2))
                }
            )
            .groupByKey()
            .mapValues(
                x => {
                    x.toList.sortBy(_._1)
                }
            )
            .print()
            
            
    }
}
