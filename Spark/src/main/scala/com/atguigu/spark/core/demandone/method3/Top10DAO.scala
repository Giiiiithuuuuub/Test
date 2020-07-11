package com.atguigu.spark.core.demandone.method3

import com.atguigu.summer.core.DAO
import com.atguigu.summer.utils.EnvUtil
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-09 18:48
 */
class Top10DAO extends DAO{
  override def readFile(path: String): RDD[String] = {
    val sparkContext : SparkContext = EnvUtil.getEnv()
    sparkContext.textFile(path)
  }
}
