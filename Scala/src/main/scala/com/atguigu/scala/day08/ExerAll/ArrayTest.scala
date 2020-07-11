package com.atguigu.scala.day08.ExerAll

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-27 23:53
 */
object ArrayTest extends App {
  val array = new Array[String](5)
  val array1 = Array(1, 2, 3, 4, 5)
  println(array.length)
  println(array1.size)

  array(1) = "zhangsan"
  array1(2) = 10

  val i = 10
  array1(i / 4) = 20
  println(array1.mkString(","))
}

object ArrayTest1 extends App {
  val array = new Array[Int](10)
  val i = 9
  array(i / 4) = 12
  println(array.mkString(","))
}

object ArrayTest2 extends App {
  val array = Array(1, 2, 3, 4, 5)
  val array1: Array[Int] = array :+ 10
  val array2: Array[Int] = 7 +: array1
  val array3: Array[Int] = array ++ array1

  array3.foreach(print)
}

object ArrayTest3 extends App {
  val array = ArrayBuffer(1, 2, 3, 4)
  val array1 = new ArrayBuffer[Int](10)

  array.append(1, 2, 4, 4)
  for (i <- array) {
    println(i)
  }
  println(array.mkString(","))
  array.foreach(println)
}

object ArrayTest4 extends App {
  val array = ArrayBuffer(1, 2, 4, 54, 2, 3)

  array.append(10)
  array.insert(2, 3)
  array.update(4, 5)
  array(2) = 100
  array.remove(5, 1)
  array.remove(2, 2)
  println(array)

  val array2: Array[Int] = array.toArray
  private val buffer: mutable.Buffer[Int] = array2.toBuffer

}

object ArrayTest5 extends App {
  val array = Array(1, 2)
  val array1 = Array(1, 2, 4)

  val array2: Array[Int] = Array.concat(array, array1)

  println(array2.mkString(","))
}

object ArrayTest6 extends App {
  val array: Array[Int] = Array.range(1, 10)
  for (i <- array) {
    print(i + "-")
  }

  //  private val function: Nothing => Array[Nothing] = Array.fill(10)

  val array2: Array[Array[Int]] = Array.fill[Int](2, 3)(100)

}

object ArrayTest7 extends App {
  val array: Array[Array[Int]] = Array.ofDim[Int](2, 3)

}

object ArrayTest8 extends App {
  val array1 = Array(1,2,3)
  val array2 = Array(4,5,6)

  val array3:Array[Int] = array1 ++: array2
  array3.foreach(println)

}

object ArrayTest9 extends App{
  val array = ArrayBuffer(1,2,3)

  val array1 : mutable.Buffer[Int] = array += 5
  print(array1.mkString(","))
}