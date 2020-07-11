package com.atguigu.spark.core.day03.homework

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-05 21:05
 */
object NODistinctTtest {
  def main(args: Array[String]): Unit = {
    new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("distinctTest")
    ).makeRDD(List(1,2,1,1,2,1,3,4),3)
      .groupBy(x => x,1)
      .map(_._1)
      .collect()
      .foreach(println)
  }
}
