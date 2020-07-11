package com.atguigu.spark.streaming.requirement3.application

import com.atguigu.spark.streaming.requirement2.cont.RequirementThreeController
import core.SummerFrame

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-18 1:33
 */
object RequirementThreeApp extends App with SummerFrame{
    
    startApp("sparkStreaming"){
        val controller = new RequirementThreeController
        
        controller.execute()
    }
}
