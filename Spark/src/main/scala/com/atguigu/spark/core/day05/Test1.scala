package com.atguigu.spark.core.day05

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-08 12:52
 */
object Test1 {
  def main(args: Array[String]): Unit = {
    new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("Test")
    ).makeRDD(List(1,1,1,2,2,2),2)
      .mapPartitions(
        iter =>{
          List((iter.next(),iter.length)).iterator
        }
      ).collect()
      .foreach(println)
  }
}
