package com.atguigu.spark.streaming.requirement.application

import com.atguigu.spark.streaming.requirement.controller.ReqThreeController
import com.atguigu.summer.core.Summer

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-17 15:48
 */
object ReqThreeApp extends App with Summer{
    
    MySummer("sparkStreaming")(null){
    
        val controller = new ReqThreeController
        
        controller.execute()
    }
}
