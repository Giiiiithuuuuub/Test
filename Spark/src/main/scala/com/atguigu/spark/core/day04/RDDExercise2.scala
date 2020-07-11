package com.atguigu.spark.core.day04

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-06 22:55
 */
object RDDExercise2 {
  def main(args: Array[String]): Unit = {
    new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("RDDExer")
    ).textFile("InputTest/agent.log")
      .map{
        line => {
          val strings: Array[String] = line.split(" ")
          (strings(1) + "-" + strings(4),1)
        }
      }
      .reduceByKey(_+_)
      .map{
        x=>{
          val strings: Array[String] = x._1.split("-")
          (strings(0),(strings(1),x._2))
        }
      }
      .groupByKey()
      .mapValues{
        x => {
          x.toList.sortBy {
            case (x: String, y: Int) => -y
          }.take(3)
        }
      }
      .collect()
      .foreach(println)
  }

}
