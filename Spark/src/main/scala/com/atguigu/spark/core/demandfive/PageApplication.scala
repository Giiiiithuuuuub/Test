package com.atguigu.spark.core.demandfive

import com.atguigu.summer.core.Summer

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-10 13:00
 */
object PageApplication extends App with  Summer{
  MySummer("spark")(null){
    val pageController : PageController = new PageController
    pageController.execute()
  }
}
