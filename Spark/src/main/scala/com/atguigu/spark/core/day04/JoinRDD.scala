package com.atguigu.spark.core.day04

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-06 22:43
 */
object JoinRDD {
  def main(args: Array[String]): Unit = {
    val context = new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("joinTest")
    )

    val value: RDD[(String, Int)] = context.makeRDD(List(("a", 1), ("b", 2), ("c", 3)))
    val value1: RDD[(String, Int)] = context.makeRDD(List(("a", 4), ("b", 3), ("c", 2)))

    value.join(value1).collect().foreach(println)


  }

}
