package com.atguigu.scala.day09

import scala.collection.parallel.immutable

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-29 8:40
 */
object WordCountTest {
  def main(args: Array[String]): Unit = {
    val dataList = List("hello scala","hello spark","hive hadoop")

    val list : List[String] = dataList.flatMap(_.split(" "))

    val map : Map[String,List[String]] = list.groupBy(x => x)

    val list2 : List[(String,List[String])] = map.toList

    val list3 :List[(String,Int)] = list2.map(x=>(x._1,x._2.size))

    val list4 : List[(String,Int)] = list3.sortBy(_._2)(Ordering.Int.reverse)

    val list5 : List[(String,Int)] = list4.take(2)

    list5.foreach(println)







  }

}
