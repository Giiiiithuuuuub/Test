package com.atguigu.spark.core.day04

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-06 12:04
 */
object CountRDD {
  def main(args: Array[String]): Unit = {
    new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("countTest")
    ).makeRDD(List((1,"a"),(1,"b"),(1,"c")))
      .countByValue()
      .foreach(println)
  }
}
