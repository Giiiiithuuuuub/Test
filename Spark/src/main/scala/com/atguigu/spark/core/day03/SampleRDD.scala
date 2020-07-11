package com.atguigu.spark.core.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-05 20:30
 */
object SampleRDD {
  def main(args: Array[String]): Unit = {
    val value: RDD[Int] = new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("sampleTest")
    ).makeRDD(List(1, 2, 3, 4), 1)

    val value1: RDD[Int] = value.sample(false, 0.5, 100)

    value1.collect().foreach(println)
  }

}

object SampleRDD1 {
  def main(args: Array[String]): Unit = {
    val value: RDD[Int] = new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("sampleTest")
    ).makeRDD(List(1, 2, 3, 4), 1)

    val value1: RDD[Int] = value.sample(true, 2, 1)

    value1.collect().foreach(println)
  }

}
