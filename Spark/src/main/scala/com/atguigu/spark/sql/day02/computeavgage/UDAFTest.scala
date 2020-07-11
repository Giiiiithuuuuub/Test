package com.atguigu.spark.sql.day02.computeavgage

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, IntegerType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-12 11:15
 */
object UDAFTest {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("udaf")

    val sparkSession: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()

    import sparkSession.implicits._

    val initRDD: RDD[(Int, String, Int)] = sparkSession.sparkContext.makeRDD(List(
      (1, "zhangsan", 20),
      (2, "lisi", 30),
      (3, "wangwu", 45)
    ))


//    val changeRDD: RDD[User] = initRDD.map {
//      case (id, name, age) => {
//        User(id, name, age)
//      }
//    }
//
//    changeRDD.toDS().createOrReplaceGlobalTempView("user")

    val myUDAF = new MyUDAF

    sparkSession.udf.register("MyUDAF",myUDAF)

    val df: DataFrame = initRDD.toDF("id", "name", "age")

    df.createOrReplaceTempView("user")

    sparkSession.sql("select myudaf(age) from user").show()//这里的sql文，不区分大小写，所以函数名大小写无所谓




  }
}

case class User(id : Int, name : String, age : Int)

class MyUDAF extends UserDefinedAggregateFunction{
  //输入的结构
  override def inputSchema: StructType = {
    StructType(Array(StructField("avgAge",IntegerType)))//结构名称、输入类型为整数；这个名称随便写不重要，用不到
  }

  //放到缓冲区的结构
  override def bufferSchema: StructType = {
    StructType(Array(
      StructField("totalage",IntegerType),
      StructField("count",IntegerType)
    ))
  }

  //返回的类型
  override def dataType: DataType = IntegerType

  //是否确定性：相同的输入是否一定有相同的输出
  override def deterministic: Boolean = true

  override def initialize(buffer: MutableAggregationBuffer): Unit = {
    buffer(0) = 0
    buffer(1) = 0
  }

  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    buffer(0) = buffer.getInt(0) + input.getInt(0)
    buffer(1) = buffer.getInt(1) + 1
  }

  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
    buffer1(0) = buffer1.getInt(0) + buffer2.getInt(0)
    buffer1(1) = buffer1.getInt(1) + buffer2.getInt(1)
  }

  override def evaluate(buffer: Row): Any = {
    buffer.getInt(0)/buffer.getInt(1)
  }
}

