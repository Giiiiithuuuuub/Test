package com.atguigu.scala.day08.ExerAll

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-28 8:54
 */
object ListTest extends App {
  val list : List[Int] = List(1,2,3)
  val list1 = list.updated(2,5)
  println(list1.mkString(","))

  println(list1)
}

object ListTest2 extends App{
  val list : List[Nothing] = List()
  val list1 = Nil

  println(list)//List()
  println(list1)//List()

  val list2 = List(8,9,10)

  val list3 : List[Int] = 1::2::3::Nil
  val list4 = 1::2::list2::Nil
  val list5 = 1::2::list2:::Nil
  val list6 = 1::2::list2:::list

  println(list3)//1,2,3
  println(list4)//1,2,3,List(8,9,10)
  println(list5)//1,2,8,9,10
  println(list6)//1,2,8,9,10

}

object ListTest3 extends App{
  private val ints: ListBuffer[Int] = ListBuffer(1, 2, 3)
//  private val ints1: ArrayBuffer[Int] = ArrayBuffer(1, 2, 3)

//  ints.insert(1,2)
private val ints1: ListBuffer[Int] = ints.updated(2, 8)
  println(ints1)
  ints.update(1,9)
  println(ints)

}

object ListTest4 extends App{
  val list = ListBuffer(1,2,3)
  val list2 = List(4,5,6)

//  private val list3: ListBuffer[Int] = list ++ list2
//  private val list1: ListBuffer[Int] = list ++= list2
//
  //  println(list.hashCode())
  //  println(list2.hashCode())
  //  println(list3.hashCode())
//  println(list1)

//  private val value: Any = 2 +: list
//  private val ints: ListBuffer[Int] = list :+ 3
//  println(value)

//  private val ints: ListBuffer[Int] = list ++ list2
//
//  val ints1 : List[Int] = list ++: list2
//
//  println(ints)
//  println(ints1)

//  list.append(10)
private val list1: list.type = list += 10
  println(list)
  println(list1)

//  private val ints: ListBuffer[Int] = list - 1

//  private val list1: list.type = list -= 5
////  println(ints)
//    println(list1)
//  println(list)

}

