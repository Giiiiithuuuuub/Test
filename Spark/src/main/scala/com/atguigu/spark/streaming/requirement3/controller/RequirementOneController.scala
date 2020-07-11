package com.atguigu.spark.streaming.requirement3.controller

import com.atguigu.spark.streaming.requirement2.serv.RequirementOneService
import core.SummerController

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-17 20:47
 */
class RequirementOneController extends SummerController{
    private val reqOneService = new RequirementOneService
    
    override def execute(): Unit = {
        val result = reqOneService.analysis()
    }
}
