package com.atguigu.spark.core.day06.DAO

import com.atguigu.summer.core.DAO
import com.atguigu.summer.utils.EnvUtil
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-09 10:38
 */
class HotCategoryTop10AnalysisDAO extends DAO{
  override def readFile(path: String): RDD[String] = {
    val sc : SparkContext = EnvUtil.getEnv()
    val value: RDD[String] = sc.textFile(path)
    value
  }
}
