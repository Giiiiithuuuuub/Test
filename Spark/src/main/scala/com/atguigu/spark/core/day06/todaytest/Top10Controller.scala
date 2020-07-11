package com.atguigu.spark.core.day06.todaytest

import com.atguigu.summer.core.Controller

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-09 14:05
 */
class Top10Controller extends Controller{
  private val top10Service = new Top10Service

  override def execute(): Unit = {
    val result: Array[(String, (Int, Int, Int))] = top10Service.analysis()

    result.foreach(println)
  }
}
