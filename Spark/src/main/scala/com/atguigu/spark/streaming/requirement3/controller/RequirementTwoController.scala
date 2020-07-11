package com.atguigu.spark.streaming.requirement3.controller

import com.atguigu.spark.streaming.requirement2.serv.RequirementTwoService
import core.SummerController

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-18 0:59
 */
class RequirementTwoController extends SummerController{
    private val reqTwoSer = new RequirementTwoService
    
    override def execute(): Unit = {
        
        val result = reqTwoSer.analysis()
    }
}
