package com.atguigu.spark.core.day03

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-05 23:20
 */
object CombineByKeyRDD {
  def main(args: Array[String]): Unit = {
    new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("CombineByKeyTest")
    ).makeRDD(List(("a",88),("b",95),("a",91),("b",93),("a",95),("a",91),("b",93)))
      .combineByKey(
        v => (v,1),
        (t:(Int,Int),v) => (t._1 + v,t._2 + 1),
        (t1:(Int,Int),t2:(Int,Int)) => (t1._1 + t2._1,t1._2+ t2._2)
      ).map(x => (x._1,x._2._1/x._2._2))
      .collect()
      .foreach(println)
  }

}
