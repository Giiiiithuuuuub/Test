package com.atguigu.spark.sql.day02.computeavgage

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.util.AccumulatorV2

import scala.collection.mutable

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-13 0:19
 */
object AvgTest2 extends App{
  val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("udaf")

  val sparkSession: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()

  import sparkSession.implicits._

  val initRDD: RDD[(Int, String, Int)] = sparkSession.sparkContext.makeRDD(List(
    (1, "zhangsan", 20),
    (2, "lisi", 30),
    (3, "wangwu", 45)
  ))
  private val accumulator = new MyAccumulator

  initRDD.foreach(
    x => accumulator.add(x._3)
  )

  println(accumulator.value)
}

class MyAccumulator extends AccumulatorV2[Int, Int] {
  var sum = 0
  var count = 0

  override def isZero: Boolean = {
    sum == 0 && count == 0
  }

  override def copy(): AccumulatorV2[Int, Int] = {
    val myAccumulator = new MyAccumulator
    myAccumulator.sum = this.sum
    myAccumulator.count = this.count
    myAccumulator
  }

  override def reset(): Unit ={
    sum == 0
    count == 0
  }

  override def add(v: Int): Unit = {
    sum += v
    count += 1
  }

  override def merge(other: AccumulatorV2[Int, Int]): Unit = {
    other match {
      case o : MyAccumulator => {
        sum += o.sum
        count += o.count
      }
      case _  =>
    }
  }

  override def value: Int = {
    sum / count
  }
}
