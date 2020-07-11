package com.atguigu.spark.streaming.requirement2.app

import com.atguigu.spark.streaming.requirement2.cont.RequirementOneController
import core.SummerFrame

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-17 20:47
 */
object RequirementOneApp extends App with SummerFrame{
    
    startApp("sparkStreaming"){
    
        val controller = new RequirementOneController
        
        controller.execute()
    }
    
}
