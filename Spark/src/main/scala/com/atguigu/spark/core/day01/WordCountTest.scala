package com.atguigu.spark.core.day01

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext, rdd}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-01 16:22
 */
object WordCountTest {
  def main(args: Array[String]): Unit = {
    //    //1. 获取连接
    //    val sparkConf : SparkConf = new SparkConf().setMaster("local").setAppName("wordcount")
    //    val sc : SparkContext = new SparkContext(sparkConf)
    //    //2. 业务处理
    //    //2.1 读取文件
    //    val input : RDD[String] = sc.textFile("Test.txt")
    //    //2.2 扁平化数据
    //    val tmep : RDD[String] = input.flatMap(_.split(" "))
    //    //2.3 格式化数据
    //    val map : RDD[(String,Int)] = tmep.map{
    //      case(word) => {
    //        (word,1)
    //      }
    //    }
    //    //2.4 规约并统计
    //    val reduce : RDD[(String,Int)] = map.reduceByKey(_+_)
    //    //2.5 收集
    //    val result : Array[(String,Int)] = reduce.collect()
    //    //2.6 输出
    //    result.foreach(println)
    //    //3. 关闭资源
    //    sc.stop()


  }
}

object WordCountTest2 {
  //  def main(args: Array[String]): Unit = {
  //    val sparkConf : SparkConf = new SparkConf().setMaster("local").setAppName("wordcount")
  //    val sc : SparkContext = new SparkContext(sparkConf)
  //    val input : RDD[String] = sc.textFile("Test.txt")
  //    val temp : RDD[String] = input.flatMap(_.split(" "))
  //    val map : RDD[(String,Int)] = temp.map((_,1))
  //    val temp2 : RDD[(String,Int)]= map.reduceByKey(_+_)
  //    val result : Array[(String,Int)] = temp2.collect()
  //    result.foreach(println)
  //    sc.stop()
  //  }
}

object WordCountTest3 {
  def main(args: Array[String]): Unit = {
    new SparkContext(new SparkConf().
      setMaster("local").
      setAppName("wordcount"))
      .textFile("Test.txt")
      .flatMap(_.split(" "))
      .map((_, 1))
      .reduceByKey(_ + _)
      .collect()
      .foreach(println)
  }
}
