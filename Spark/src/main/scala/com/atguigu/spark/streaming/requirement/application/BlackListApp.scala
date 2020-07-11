package com.atguigu.spark.streaming.requirement.application

import com.atguigu.spark.streaming.requirement.controller.BlackListController
import com.atguigu.summer.core.Summer

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-16 20:53
 */
object BlackListApp extends App with Summer{
    MySummer("sparkStreaming")(null){
        val controller = new BlackListController
        
        controller.execute()
    }
}
