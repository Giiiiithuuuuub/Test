package com.atguigu.spark.sql.day02.computeavgage

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-13 0:04
 */
object AvgAgeTest {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("udaf")

    val sparkSession: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()

    import sparkSession.implicits._

    val initRDD: RDD[(Int, String, Int)] = sparkSession.sparkContext.makeRDD(List(
      (1, "zhangsan", 20),
      (2, "lisi", 30),
      (3, "wangwu", 45)
    ))

    val tuple: (Int, Int) = initRDD
      .map(x => (x._3, 1))
      .reduce((x, y) => (x._1 + y._1, x._2 + y._2))

    println(tuple._1/tuple._2)




  }
}
