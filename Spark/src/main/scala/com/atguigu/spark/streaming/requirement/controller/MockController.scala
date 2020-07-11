package com.atguigu.spark.streaming.requirement.controller

import com.atguigu.spark.streaming.requirement.service.MockService
import com.atguigu.summer.core.Controller

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-16 0:50
 */
class MockController extends Controller{
    private val mockService : MockService  = new MockService
    
    override def execute(): Unit = {
        val result = mockService.analysis()
    }
}
