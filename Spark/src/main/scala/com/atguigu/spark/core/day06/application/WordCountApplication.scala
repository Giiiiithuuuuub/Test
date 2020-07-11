package com.atguigu.spark.core.day06.application

import com.atguigu.spark.core.day06.controller.WordCountController
import com.atguigu.summer.core.Summer
import org.apache.spark.SparkContext

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-08 22:09
 */
object WordCountApplication extends App with Summer{
  MySummer("spark")(null) {

    val controller = new WordCountController
    controller.execute()
  }
}
