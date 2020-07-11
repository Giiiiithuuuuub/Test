package com.atguigu.spark.core.day06.controller

import com.atguigu.spark.core.day06.service.HotCategoryTop10AnalysisService
import com.atguigu.summer.core.Controller

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-09 10:38
 */
class HotCategoryTop10AnalysisController extends Controller{
  private val hotCategoryTop10AnalysisService = new HotCategoryTop10AnalysisService

  override def execute()= {
    val result: Array[(String, (Int, Int, Int))] = hotCategoryTop10AnalysisService.analysis()

    result.foreach(println)
  }
}
