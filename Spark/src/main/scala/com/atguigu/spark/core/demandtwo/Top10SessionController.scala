package com.atguigu.spark.core.demandtwo

import com.atguigu.spark.core.demandone.method5
import com.atguigu.summer.core.Controller

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-09 18:51
 */
class Top10SessionController extends Controller{
  private val top10Service = new Top10Service
  private val top10SessionService = new Top10SessionService

  override def execute(): Unit = {
    val serviceResult= top10Service.analysis()
    val sessionServiceResult: Array[(String, List[(String, Int)])] = top10SessionService.analysis(serviceResult)

    sessionServiceResult.foreach(println)

  }
}
