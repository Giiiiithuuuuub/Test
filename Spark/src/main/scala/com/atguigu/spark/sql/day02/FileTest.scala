package com.atguigu.spark.sql.day02

import com.atguigu.spark.core.sql.day01.User
import org.apache.spark
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-13 0:55
 */
object FileTest {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().set("spark.sql.sources.default","json").setMaster("local[*]").setAppName("udaf")

    val sparkSession: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()

    import sparkSession.implicits._

    val initRDD: RDD[(Int, String, Int)] = sparkSession.sparkContext.makeRDD(List(
      (1, "zhangsan", 20),
      (2, "lisi", 30),
      (3, "wangwu", 45)
    ))
//
//    val dataFrame: DataFrame = sparkSession.read.format("json").load("InputTest/jsonTest.json")
//
//    dataFrame.show()
//
    val frame: DataFrame = initRDD.toDF()
//
//    frame.write.format("json").save("output")

//    sparkSession.sql("select * from json.`InputTest/jsonTest.json`").show()
//
//    frame.write.save("output")

//      val value: Dataset[(Int, String, Int)] = initRDD.toDS()

//      value.createOrReplaceTempView("name")

  }
}
