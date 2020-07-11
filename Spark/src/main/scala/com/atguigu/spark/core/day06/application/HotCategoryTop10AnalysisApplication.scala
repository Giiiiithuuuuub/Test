package com.atguigu.spark.core.day06.application

import com.atguigu.spark.core.day06.controller.HotCategoryTop10AnalysisController
import com.atguigu.summer.core.Summer

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-09 10:36
 */
object HotCategoryTop10AnalysisApplication extends App with Summer{

  //热门品类前十应用程序
  MySummer("spark")(null){
    val controller = new HotCategoryTop10AnalysisController
    controller.execute()

  }

}
