package com.atguigu.spark.streaming.requirement3.controller

import com.atguigu.spark.streaming.requirement2.serv.SimulateDataService
import core.SummerController

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-17 19:57
 */
class SimulateDataController extends SummerController{
    private val simulateDataService = new SimulateDataService
    
    override def execute(): Unit = {
        val result = simulateDataService.analysis()
        
    }
}
