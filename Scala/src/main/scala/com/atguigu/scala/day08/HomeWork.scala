package com.atguigu.scala.day08

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-28 15:47
 */
object HomeWork {
  def main(args: Array[String]): Unit = {
    val list: List[(String, Int)] = List(
      ("hello", 4),
      ("hello spark", 3),
      ("hello spark scala", 2),
      ("hello spark scala hive", 1)
    )

    //1. 创建一个可变list
    val list2: ListBuffer[(String, Int)] = ListBuffer[(String, Int)]()

    //2. 遍历集合中的每个元组，将其拆分为key-v,添加到集合中
    for (i <- list.indices) {
      val strings: Array[String] = list(i)._1.split(" ")
      for (j <- strings) {
        list2.append((j, list(i)._2))
      }

    }

    //3. 排序
    val list3: ListBuffer[(String, Int)] = list2.sortBy(x => x._1)

    val list4 = new ListBuffer[(String, Int)]()
    //4. 遍历元素，合并
    var sum: Int = 0
    for (i <- 0 to list3.length - 2) {

      if (list3(i)._1 == list3(i + 1)._1 && i != list3.length - 2) {
        sum += list3(i)._2
      } else if (i == list3.length - 2) {
        list4.append((list3(i)._1, sum + list3(i)._2 + list3(i + 1)._2))
      } else {
        list4.append((list3(i)._1, sum + list3(i)._2))
        sum = 0
      }
    }

    //5. 排序
    list4.sortBy(-_._2)
      .take(3)
      .foreach(println)

  }
}