package com.atguigu.spark.streaming.requirement3.controller

import com.atguigu.spark.streaming.requirement2.serv.RequirementThreeService
import core.SummerController

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-18 1:33
 */
class RequirementThreeController extends SummerController{
    private val reqThreeSer = new RequirementThreeService
    
    override def execute(): Unit = {
        val result = reqThreeSer.analysis()
    }
}
