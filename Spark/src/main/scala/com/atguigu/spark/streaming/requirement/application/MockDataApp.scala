package com.atguigu.spark.streaming.requirement.application

import com.atguigu.spark.streaming.requirement.controller.MockController
import com.atguigu.summer.core.{Summer}


/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-16 0:23
 */
object MockDataApp extends App with Summer {
    
    MySummer("sparkStreaming")("summer.properties"){
        val controller : MockController = new MockController
        
        controller.execute()
    }
}
