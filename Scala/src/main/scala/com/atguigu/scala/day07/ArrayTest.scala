package com.atguigu.scala.day07

import scala.collection.mutable.ArrayBuffer

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-26 16:02
 */
object ArrayTest {
  def main(args: Array[String]): Unit = {
    val array = new Array[String](5)
    array.+:("5")
    println(array)

    val ints: Array[Int] = Array(1, 2, 3, 4)
    val buffer: ArrayBuffer[Int] = ArrayBuffer(1, 2, 3)

    buffer.insert(1, 7, 8, 9)
    println(buffer)

  }
}
