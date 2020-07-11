package com.atguigu.scala.day08

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-29 9:06
 */
object HomeWord3 {
  def main(args: Array[String]): Unit = {
//    val list: List[(String, Int)] = List(
//      ("hello", 4),
//      ("hello spark", 3),
//      ("hello spark scala", 2),
//      ("hello spark scala hive", 1)
//    )
//
//    list.map(x => (x._1 + " ") * x._2)
//      .flatMap(_.split(" "))
//      .groupBy(x => x)
//      .toList
//      .map(x => (x._1, x._2.size))
//      .sortBy(-_._2)
//      .take(3)
//      .foreach(println)


  }
}

object Test12341{
  def main(args: Array[String]): Unit = {
    val list = List(1,2,4,5,6)
    val iterator: Iterator[List[Int]] = list.sliding(2)

    iterator.foreach(println)
  }
}

