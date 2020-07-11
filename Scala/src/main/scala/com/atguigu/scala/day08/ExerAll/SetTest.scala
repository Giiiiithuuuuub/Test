package com.atguigu.scala.day08.ExerAll

import scala.collection.mutable

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-28 10:23
 */
object SetTest {
  def main(args: Array[String]): Unit = {
    val set : Set[Int] = Set(1, 2, 3,4,5,6,7,8,9)

    val bool: Boolean = set(0)
    val bool1: Boolean = set(1)

    println(bool)//false
    println(bool1)//true

  }
}

object SetTest2{
  def main(args: Array[String]): Unit = {
    val set: mutable.Set[Int] = mutable.Set(1,2,3)

//    set.add(1)
    set.update(1,included = false)

set.remove(2)

    println(set)
  }
}

object SetTest3{
  def main(args: Array[String]): Unit = {
    val set: mutable.Set[Int] = mutable.Set(1,2,3,4,5,9)
    val set1: mutable.Set[Int] = mutable.Set(2,4,5,9)

    val set2 :mutable.Set[Int] = set & set1

    println(set2)

    val set3 : mutable.Set[Int] = set1 &~ set
    println(set3)

    val set4: Set[Int] = set.toSet

  }
}