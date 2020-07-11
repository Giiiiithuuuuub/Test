package com.atguigu.spark.core.sql.day01

import com.atguigu.summer.utils.EnvUtil
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, sql}
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-10 16:46
 */
object TestDF {
  def main(args: Array[String]): Unit = {
    val sparkSession: sql.SparkSession = SparkSession.
      builder().
      config(new SparkConf()
        .setMaster("local[*]")
        .setAppName("SparkSql"))
      .getOrCreate()

    import sparkSession.implicits._
    val df : DataFrame = sparkSession.read.json("InputTest/jsonTest.json")

    df.show()

    df.createOrReplaceGlobalTempView("user")

    sparkSession.sql("select * from global_temp.user").show()

    df.select("name").show()
    df.filter("age > 20").show()
    df.printSchema()

    df.select('age + 1).show()

    val rddTest: RDD[Int] = sparkSession.sparkContext.makeRDD(List(1, 2, 3, 4, 5))

    val dfTest: DataFrame = rddTest.toDF("id")
    val rdd: RDD[Row] = df.rdd


    val ds: Dataset[User] = df.as[User]
    val rdd1 : RDD[User] = ds.rdd
    val value: Dataset[User] = rdd1.toDS()
    val dsTodf: DataFrame = ds.toDF()
  }
}

case class User(name : String, age : BigInt)

object TsetTake{
  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4,5,6)

    val iterator: Iterator[List[Int]] = list.sliding(2)

    iterator.toList
      .map(list => (list(0),list(1)))
      .foreach(println)
  }
}
