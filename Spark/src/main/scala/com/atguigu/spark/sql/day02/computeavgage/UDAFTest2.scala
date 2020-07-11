package com.atguigu.spark.sql.day02.computeavgage

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.expressions.Aggregator
import org.apache.spark.sql.{Dataset, Encoder, Encoders, SparkSession}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-12 11:15
 */
object UDAFTest2 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("udaf2")

    val sparkSession: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()

    import sparkSession.implicits._

    val initRDD: RDD[(Int, String, Int)] = sparkSession.sparkContext.makeRDD(List(
      (1, "zhangsan", 20),
      (2, "lisi", 30),
      (3, "wangwu", 45)
    ))


    val changeRDD: RDD[User1] = initRDD.map {
      case (id, name, age) => {
        User1(id, name, age)
      }
    }

    val ds: Dataset[User1] = changeRDD.toDS()

    val zenmezongshicuo = new MyUDAF2

    ds.select(zenmezongshicuo.toColumn).show()


  }
}

case class User1(id : Int, name : String, age : Int)//用来将RDD封装为类型的样例类

case class AvgBuffer(var totalage:Int , var count:Int)//用来定义缓冲区的类型

class MyUDAF2 extends Aggregator[User1,AvgBuffer,Int]{
  override def zero: AvgBuffer = AvgBuffer(0,0)

  override def reduce(b: AvgBuffer, a: User1): AvgBuffer = {
    b.totalage = b.totalage + a.age
    b.count = b.count + 1

    b
  }

  override def merge(b1: AvgBuffer, b2: AvgBuffer): AvgBuffer = {
    b1.totalage = b1.totalage + b2.totalage
    b1.count = b1.count + b2.count
    b1
  }

  override def finish(reduction: AvgBuffer): Int = {
    reduction.totalage/reduction.count
  }

  override def bufferEncoder: Encoder[AvgBuffer] = Encoders.product

  override def outputEncoder: Encoder[Int] = Encoders.scalaInt
}

