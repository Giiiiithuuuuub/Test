package com.atguigu.spark.core.day05

import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-08 19:38
 */
object AccumulatorTest {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("accTest")
    )
    val list: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))
    //声明累加器
    val sum: LongAccumulator = sc.longAccumulator("sum")
    list.foreach(
      x =>{
        //使用累加器
        sum.add(x)
      }
    )
    //获取累加器结果
    println(sum.value)
    sc.stop()
  }
}

object AccumulatorTest1 {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("accTest")
    )
    val list: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))
    //声明累加器
    val sum: LongAccumulator = sc.longAccumulator("sum")
    val test: RDD[Unit] = list.map {
      x => {
        sum.add(x)
        x
      }
    }

    println(test.collect().mkString(","))
    test.foreach(println)
    //获取累加器结果
    println(sum.value)
    sc.stop()
  }
}
