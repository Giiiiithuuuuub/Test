package com.atguigu.spark.core.day04

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-06 10:58
 */
object RDDExercise {
  def main(args: Array[String]): Unit = {
    val sparkContext: SparkContext = new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("RDDTest")
    )
    sparkContext
      .textFile("InputTest/agent.log")
      .map(
        x => {
          val datas = x.split(" ")
          (datas(1) + "-" + datas(4), 1)
        }
      )
      .reduceByKey(_ + _)
      .map(
        x => {
          val temp = x._1.split("-")
          (temp(0), (temp(1), x._2))
        }
      )
      .groupByKey
      .mapValues {
        x => {
          x
            .toList
            .sortBy {
              case (key, value) => -value
            }.take(3)
        }
      }
      .collect()
      .foreach(println)
    sparkContext.stop()
  }

}
