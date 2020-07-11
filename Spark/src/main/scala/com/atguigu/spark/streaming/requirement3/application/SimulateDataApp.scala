package com.atguigu.spark.streaming.requirement3.application

import com.atguigu.spark.streaming.requirement2.cont.SimulateDataController
import core.SummerFrame

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-17 19:39
 */
object SimulateDataApp extends App with SummerFrame{
    
    startApp("sparkStreaming"){
        val controller = new SimulateDataController
        
        controller.execute()
    }
}
