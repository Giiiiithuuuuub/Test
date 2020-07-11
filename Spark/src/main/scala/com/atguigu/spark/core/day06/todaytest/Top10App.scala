package com.atguigu.spark.core.day06.todaytest

import com.atguigu.summer.core.Summer

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-09 14:06
 */
object Top10App extends App with Summer{
  MySummer("spark")(null){
    val controller = new Top10Controller

    controller.execute()
  }
}
