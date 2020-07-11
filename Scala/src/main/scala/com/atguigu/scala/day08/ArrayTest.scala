package com.atguigu.scala.day08

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-27 20:01
 */
object ArrayTest extends App {
  private val array: Array[Array[Int]]  = Array.ofDim[Int](3, 5)


  println(array(0).length)
  println(array(1).length)


  private val array1: Array[Array[Int]] = Array(Array(1, 2, 3), Array(1, 2))
  println(array1(0).length)
  println(array1(1).length)

}

object FlatMapTest{
  def main(args: Array[String]): Unit = {
    val list : List[String] = List("scala world", "nihao hadoop")

    println(list.flatten)

    println(list.flatMap(_.split(" ")))
  }
}
