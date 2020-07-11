package com.atguigu.spark.core.demandtwo

import com.atguigu.summer.core.Summer

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-09 19:38
 */
object Top10SessionApplication extends App with Summer{
  MySummer("spark")(null){
    val controller : Top10SessionController = new Top10SessionController
    controller.execute()
  }
}
