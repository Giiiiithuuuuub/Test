package com.atguigu.scala.day08

import scala.collection.mutable.ListBuffer

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-28 17:40
 */
object HomeWork2 {
  def main(args: Array[String]): Unit = {
    val list: List[(String, Int)] = List(
      ("hello", 4),
      ("hello spark", 3),
      ("hello spark scala", 2),
      ("hello spark scala hive", 1)
    )

    //创建一个空集合
    val container : ListBuffer[String] = ListBuffer[String]()

    val list1: List[(List[String], Int)] = list.map(x => (x._1.split(" ").toList, x._2))

    //处理数据，添加到空集合中
    for (i <- list1.indices){
      for (j <- list1(i)._1.indices){
        container.append((list1(i)._1(j) + " ") * list1(i)._2)
      }
    }

    //逻辑处理
    container.flatMap(_.split(" "))
      .groupBy(x => x)
      .toList
      .map(x => (x._1,x._2.length))
      .sortBy(-_._2)
      .take(3)
      .foreach(println)
  }
}
