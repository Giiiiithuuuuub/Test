package com.atguigu.spark.streaming.requirement2.app

import com.atguigu.spark.streaming.requirement2.cont.RequirementTwoController
import core.SummerFrame

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-18 0:58
 */
object RequirementTwoApp extends App with SummerFrame{
    
    startApp("sparkStreaming"){
    
        val controller = new RequirementTwoController
        
        controller.execute()
    }
}
