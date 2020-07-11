package com.atguigu.spark.core.day03

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-05 21:02
 */
object DistinctRDD {
  def main(args: Array[String]): Unit = {
    new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("distinctTest")
    ).makeRDD(List(1,2,1,1,2,1,3,4),3)
      .distinct(2)
      .collect()
      .foreach(println)
  }
}
