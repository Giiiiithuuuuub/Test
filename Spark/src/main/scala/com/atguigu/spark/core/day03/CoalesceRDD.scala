package com.atguigu.spark.core.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{Partitioner, SparkConf, SparkContext}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-05 21:21
 */
object CoalesceRDD {
  def main(args: Array[String]): Unit = {

    val value: RDD[Int] = new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("coalesceTest")
    ).makeRDD(List(1, 2, 3, 4, 5, 6), 3)
    val value1: RDD[Int] = value.coalesce(2,true)

    value1.saveAsTextFile("output")

  }
}
