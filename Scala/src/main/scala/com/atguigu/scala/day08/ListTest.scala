package com.atguigu.scala.day08

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-27 15:26
 */
object ListTest {

  def main(args: Array[String]): Unit = {
    val list = List("scala hadoop","hello world")

    println(list.flatMap(_.split(" ")))

    def transform(list : String) ={
      list.split(" ")
    }

    val list1 = List(1,2,3,4)

    println(list1.sortBy(-_))

  }

}

object List123{
  def main(args: Array[String]): Unit = {
    val ints = List(1, 2, 3, 4, 5)

    val iterator: Iterator[List[Int]] = ints.sliding(2)

    while (iterator.hasNext){
      println(iterator.next())
    }
  }
}
