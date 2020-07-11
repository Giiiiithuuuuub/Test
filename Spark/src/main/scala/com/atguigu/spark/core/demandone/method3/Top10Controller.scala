package com.atguigu.spark.core.demandone.method3

import com.atguigu.summer.core.Controller

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-09 18:51
 */
class Top10Controller extends Controller{
  private val top10Service = new Top10Service

  override def execute(): Unit = {
    val serviceResult : Array[(String,(Int,Int,Int))] = top10Service.analysis()

    serviceResult.foreach(println)
  }
}
