package com.atguigu.spark.core.day02

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-04 0:02
 */
object GroupByRDD {
  def main(args: Array[String]): Unit = {
    new SparkContext(
      new SparkConf()
        .setMaster("local")
        .setAppName("groupByTest")
    ).makeRDD(List(1, 2, 3, 4, 5, 6), 3)
      .groupBy(
        x => x % 2, 2
      ).collect()
      .foreach(println)
  }

}

object GroupByRDD2 {
  def main(args: Array[String]): Unit = {
    new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("test1")
    ).makeRDD(List("Hello", "hive", "hbase", "Hadoop"), 2)
      .groupBy(x => x.startsWith("H"))
      .collect()
      .foreach {
        case (x, y) => {
            println(y.mkString(","))
        }
      }

  }
}

object GroupByRDD3{
  def main(args: Array[String]): Unit = {
    new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("test2")
    ).textFile("inputTest/apache.log")
      .map(_.split(" ")(3).split(":"))
      .groupBy(_(0))
      .map(x => (x._1,x._2.size))
      .collect()
      .foreach(println)
  }
}

object GroupByRDD4{
  def main(args: Array[String]): Unit = {
    new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("test3")
    ).textFile("inputTest/Test.txt")
      .flatMap(_.split(" "))
      .map((_,1))
      .reduceByKey(_+_)
      .collect()
      .foreach(println)

  }
}

object GroupByRDD5{
  def main(args: Array[String]): Unit = {
    new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("test3")
    ).textFile("inputTest/Test.txt")
      .flatMap(_.split(" "))
      .groupBy(x => x)
      .map(x => (x._1,x._2.size))
      .collect()
      .foreach(println)

  }
}