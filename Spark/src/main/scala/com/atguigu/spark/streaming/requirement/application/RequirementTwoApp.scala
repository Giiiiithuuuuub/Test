package com.atguigu.spark.streaming.requirement.application

import com.atguigu.spark.streaming.requirement.controller.RequirementTwoController
import com.atguigu.summer.core.Summer

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-17 14:15
 */
object RequirementTwoApp extends App with Summer{
    MySummer("sparkStreaming")(null){
        val reqTwoController = new RequirementTwoController
        
        reqTwoController.execute()
    }
}
