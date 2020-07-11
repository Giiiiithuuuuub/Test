package com.atguigu.spark.core.demandfour

import com.atguigu.summer.core.Summer

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-09 19:38
 */
object PageApplication extends App with Summer{
  MySummer("spark")(null){
    val controller : PageController = new PageController
    controller.execute()
  }
}
