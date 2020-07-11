package com.atguigu.spark.streaming.requirement.controller

import com.atguigu.spark.streaming.requirement.service.ReqThreeService
import com.atguigu.summer.core.Controller
import org.apache.spark.streaming.dstream.DStream

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-17 15:48
 */
class ReqThreeController extends Controller{
    private val reqThreeService = new ReqThreeService
    
    override def execute(): Unit = {
        val result = reqThreeService.analysis()
    }
}
