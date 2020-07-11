package com.atguigu.scala.day07

import scala.collection.mutable

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-26 22:07
 */
object ArrayTest2 extends App {

  val array1 = new Array[String](3)
  val array = Array(1,3,5,7)

//  array(0) = 2
//  for(i <- array){
//    print(i)
//  }
//
//  private val str: String = array.mkString(",")
//  println(str)
//
//  private val ints: Array[Int] = array :+ 2
//  println(ints.mkString(","))
//
//  private val ints2: Array[Int] = array.+:(10)
//  private val ints1: Array[Int] = 12 +: array
//
//  println(ints2.mkString(","))
//  println(ints1.mkString(","))

  private val buffer: mutable.Buffer[Int] = array.toBuffer

//  println(buffer)

  buffer.append(1234)

  buffer.remove(1,2)
  buffer.insert(1,2,2,2,2)
  buffer.update(6,10)

  private val array2: Array[Int] = buffer.toArray
  println(array2.mkString(","))


  println(buffer)
}
