package com.atguigu.spark.core.day02

import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-04 0:44
 */
object FilterRDD {

  def main(args: Array[String]): Unit = {
    new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("filterTest")
    ).textFile("inputTest/apache.log")
      .filter(_.split(" ")(3).split(":")(0) == "17/05/2015")
      .map(_.split(" ")(6))
      .foreach(println)

//    new HashPartitioner()
  }

}
