package com.atguigu.spark.core.demandfive

import com.atguigu.summer.core.Controller

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-10 13:01
 */
class PageController extends Controller{
  private val pageService = new PageService
  override def execute(): Unit = {
    pageService.analysis()
  }
}
