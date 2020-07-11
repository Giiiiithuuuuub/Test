package com.atguigu.scala.day08

import scala.io.{BufferedSource, Source}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-27 16:39
 */
object WordCountTest {

  def main(args: Array[String]): Unit = {
    //noinspection SourceNotClosed
    Source
      .fromFile("Test.txt")
      .getLines
      .toList
      .flatMap(_.split(" "))
      .groupBy(x => x)
      .toList
      .map(x => (x._1,x._2.size))
      .sortBy(-_._2)
      .take(3)
      .foreach(println)
  }
}
