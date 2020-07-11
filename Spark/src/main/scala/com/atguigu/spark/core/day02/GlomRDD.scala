package com.atguigu.spark.core.day02

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-03 23:37
 */
object GlomRDD {
  def main(args: Array[String]): Unit = {
    println(new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("glomTest")
    ).makeRDD(List(1, 2, 3, 4, 5, 6, 7, 8), 3)
      .glom()
      .map(_.max)
      .reduce(_ + _))
  }

}
