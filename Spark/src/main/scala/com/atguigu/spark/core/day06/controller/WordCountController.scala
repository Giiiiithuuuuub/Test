package com.atguigu.spark.core.day06.controller

import com.atguigu.spark.core.day06.service.WordCountService
import com.atguigu.summer.core.Controller

//wordCount控制器
class WordCountController extends Controller{
  private val wordCountService = new WordCountService
  override def execute(): Unit = {
    val wordCountArray : collection.Map[String, Long] = wordCountService.analysis()
    wordCountArray.foreach(println)
  }
}
