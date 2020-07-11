package com.atguigu.spark.core.day02

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-03 23:09
 */
object MapPartitionsWithIndexRDD {
  def main(args: Array[String]): Unit = {

    val sc : SparkContext = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("mapIndexTest"))

    val value: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4), 2)

    value.mapPartitionsWithIndex(
      (x,y)=> List((x,y.max)).iterator
    ).collect()
      .foreach(println)
  }

}

object MapPartitionsWithIndexRDD2{
  def main(args: Array[String]): Unit = {
    new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("mapIndex")
    ).makeRDD(List(1,2,3,4),2)
      .mapPartitionsWithIndex(
        (x,y)=>{
          if (x == 1){
            y
          }else{
            Nil.iterator
          }
        }
      ).collect()
      .foreach(println)
  }
}
