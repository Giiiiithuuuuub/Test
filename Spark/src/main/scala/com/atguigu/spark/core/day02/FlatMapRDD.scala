package com.atguigu.spark.core.day02

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-03 23:21
 */
object FlatMapRDD {
  def main(args: Array[String]): Unit = {
    val value: RDD[List[Int]] = new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("flatMap")
    ).makeRDD(List(List(1, 2), List(3, 4)))

    val value1: RDD[Int] = value.flatMap(x => x)

    value1.foreach(println)
  }

}

object FlatMapRDD2 {
  def main(args: Array[String]): Unit = {
    new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("flatMap")
    ).makeRDD(List(List(1, 2), 3, List(4, 5)))
      .flatMap {
        x => {
          x match {
            case list: List[_] => list
            case d => List(d)
          }
        }
      }.collect
      .foreach(println)
  }
}