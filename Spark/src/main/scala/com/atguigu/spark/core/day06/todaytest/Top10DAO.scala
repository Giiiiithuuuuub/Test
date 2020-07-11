package com.atguigu.spark.core.day06.todaytest

import com.atguigu.summer.core.DAO
import com.atguigu.summer.utils.EnvUtil
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-09 14:01
 */
class Top10DAO extends DAO{
  override def readFile(path: String) = {
    val value: RDD[String] = EnvUtil.getEnv().textFile(path)
    value
  }
}
