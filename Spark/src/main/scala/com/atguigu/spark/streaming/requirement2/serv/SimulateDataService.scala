package com.atguigu.spark.streaming.requirement2.serv

import com.atguigu.spark.streaming.requirement2.dao.SimulateDataDAO
import core.SummerService

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-17 19:58
 */
class SimulateDataService extends SummerService{
    private val simulateDataDAO = new SimulateDataDAO
    
    override def analysis() = {
    
        val datas: () => Seq[String] = simulateDataDAO.generateData _
        
        simulateDataDAO.writeToKafka("requirement.properties")(datas)
    }
}
