package com.atguigu.spark.core.day02

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-03 11:05
 */
object Test1 {
  def main(args: Array[String]): Unit = {
    val wordCount: SparkConf = new SparkConf().setMaster("local[*]").setAppName("wordCount")
//    val value: RDD[Int] = new SparkContext(wordCount).makeRDD(List(1, 2, 3, 4, 5), 3)

    val value: RDD[String] = new SparkContext(wordCount).textFile("Test.txt")
    value.saveAsTextFile("output")

  }

}
