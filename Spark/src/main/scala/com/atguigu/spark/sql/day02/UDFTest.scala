package com.atguigu.spark.sql.day02

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-12 22:38
 */
object UDFTest {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("udf")

    val sparkSession: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()

    import sparkSession.implicits._

    val initRDD: RDD[(Int, String, Int)] = sparkSession.sparkContext.makeRDD(List(
      (1, "zhangsan", 20),
      (2, "lisi", 30),
      (3, "wangwu", 45)
    ))

    val df: DataFrame = initRDD.toDF("id", "name", "age")

    df.show()

    df.createOrReplaceTempView("people")

    sparkSession.sql("select * from people").show()

    sparkSession.udf.register("UDFTest",(x:String) => "UDF:" + x)

    sparkSession.sql("select UDFTest(name) from people").show()




  }
}
