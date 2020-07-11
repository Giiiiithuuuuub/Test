package com.atguigu.spark.streaming.requirement3.service

import com.atguigu.spark.streaming.requirement3.dao.{RequirementOneDAO, SimulateDataDAO}
import core.SummerService

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-17 19:58
 */
class SimulateDataService extends SummerService{
    private val simulateDAO = new SimulateDataDAO
    override def analysis() = {
    
        val datas : ()=> Seq[String] = simulateDAO.generateData _
        
        
        simulateDAO.writeToKafka("requirement.properties")(datas)
    }
}
