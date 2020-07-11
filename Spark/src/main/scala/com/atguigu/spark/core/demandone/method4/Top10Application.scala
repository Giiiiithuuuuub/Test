package com.atguigu.spark.core.demandone.method4

import com.atguigu.summer.core.Summer

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-09 19:38
 */
object Top10Application extends App with Summer{
  MySummer("spark")(null){
    val controller : Top10Controller = new Top10Controller
    controller.execute()
  }
}
