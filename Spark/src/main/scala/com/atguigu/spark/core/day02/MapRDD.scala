package com.atguigu.spark.core.day02

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-03 22:28
 */
object MapRDD {
  def main(args: Array[String]): Unit = {
    val sparkConf : SparkConf = new SparkConf().setMaster("local[*]").setAppName("mapTest")
    val sc : SparkContext = new SparkContext(sparkConf)

    val mapRDD : RDD[Int] = sc.makeRDD(List(1,2,3,4),2)

    val resultRDD: RDD[Int] = mapRDD.map(_ * 2)

    val result: Array[Int] = resultRDD.collect()

    result.foreach(println)
  }

}

object MapRDD2{
  def main(args: Array[String]): Unit = {
    val sparkConf : SparkConf = new SparkConf().setMaster("local[*]").setAppName("mapExercise")
    val sc : SparkContext = new SparkContext(sparkConf)

    val mapRDD : RDD[String] = sc.textFile("inputTest/apache.log")

    val resultRDD : RDD[String] = mapRDD.map(x => x.split(" ")(6))

    val result : Array[String] = resultRDD.collect()

    result.foreach(println)
  }
}

object MapRDD3{
  def main(args: Array[String]): Unit = {
    new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("mapTest")
    ).textFile("inputTest")
      .map(_.split(" ")(6))
      .collect
      .foreach(println)
  }
}
