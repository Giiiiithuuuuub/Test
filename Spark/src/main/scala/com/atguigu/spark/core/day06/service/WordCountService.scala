package com.atguigu.spark.core.day06.service

import com.atguigu.spark.core.day06.DAO.WordCountDAO
import com.atguigu.spark.core.day06.application.WordCountApplication.envData
import com.atguigu.summer.core.Service
import com.atguigu.summer.utils.EnvUtil
import org.apache.spark.SparkContext

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-08 23:01
 */
class WordCountService extends Service{
  private val wordCountDAO = new WordCountDAO
  override def analysis(): collection.Map[String, Long] = {
    wordCountDAO.readFile("InputTest/Test.txt")
      .flatMap(_.split(" "))
      .countByValue()

  }
}
