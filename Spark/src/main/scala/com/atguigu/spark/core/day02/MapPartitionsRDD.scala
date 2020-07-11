package com.atguigu.spark.core.day02

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-03 22:57
 */
object MapPartitionsRDD {
  def main(args: Array[String]): Unit = {
    new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("mapPartitions")
    ).makeRDD(List(1,2,3,4,5),2)
      .mapPartitions(
        x => Array(x.max).iterator
      ).collect()
      .foreach(println)
  }
}

object MapParationsRDD2{
  def main(args: Array[String]): Unit = {
    val context = new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("mapParations")
    )

    val value: RDD[Int] = context.makeRDD(List(1, 2, 3, 4, 5), 2)
    val result : RDD[Int] = value.mapPartitions(x => List(x.max).iterator)
  }
}
