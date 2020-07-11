package com.atguigu.scala.day09.homework

import scala.collection.mutable

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-29 23:54
 */
object Test{
  def main(args: Array[String]): Unit = {
    val que = new mutable.Queue[String]()
    // 添加元素
    que.enqueue("a", "b", "c")
    val que1: mutable.Queue[String] = que += "d"
    println(que eq que1)
    // 获取元素
    println(que.dequeue())
    println(que.dequeue())
    println(que.dequeue())
  }
}
