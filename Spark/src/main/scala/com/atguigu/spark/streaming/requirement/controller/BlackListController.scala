package com.atguigu.spark.streaming.requirement.controller

import com.atguigu.spark.streaming.requirement.service.BlackListService
import com.atguigu.summer.core.Controller

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-16 22:26
 */
class BlackListController extends Controller{
    private val blackListService = new BlackListService()
    
    override def execute(): Unit = {
        val result = blackListService.analysis()
    }
}
