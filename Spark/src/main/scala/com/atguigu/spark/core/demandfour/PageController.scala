package com.atguigu.spark.core.demandfour

import com.atguigu.summer.core.Controller

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-09 18:51
 */
class PageController extends Controller{
  private val pageService = new PageService

  override def execute(): Unit = {
    val serviceResult: Unit = pageService.analysis()

  }
}
