package com.atguigu.scala.day08

import scala.io.{BufferedSource, Source}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-27 21:43
 */
object WordCountTest2 {

  def main(args: Array[String]): Unit = {

    //获取文件源
    val source : BufferedSource = Source.fromFile("Test.txt")
    //获取迭代器
    val iterator : Iterator[String] = source.getLines()
    //获取集合
    val list : List[String] = iterator.toList
    //扁平化处理
    val list2 : List[String] = list.flatMap(_.split(" "))
    //分组
    val map : Map[String,List[String]] = list2.groupBy(x => x)
    //获取list
    val list3 : List[(String,List[String])] = map.toList
    //count
    val list4 : List[(String,Int)] = list3.map(x => (x._1,x._2.length))
    //排序
    val list5 : List[(String,Int)] = list4.sortBy(-_._2)
    //截取
    val list6 : List[(String,Int)] = list5.take(3)
    //打印
    list6.foreach(println)
    //关闭资源
    source.close()
  }

}
