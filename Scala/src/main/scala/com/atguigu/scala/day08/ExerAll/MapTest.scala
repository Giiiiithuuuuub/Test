package com.atguigu.scala.day08.ExerAll

import scala.collection.mutable

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-28 11:29
 */
object MapTest {
  def main(args: Array[String]): Unit = {
    val map : Map[String,Int] = Map("a" -> 1)

    val map1: Map[String, Int] = map + ("b" -> 1)

    println(map1.apply("b"))
    println(map1("b"))

    val empty: Map[Nothing, Nothing] = Map.empty


  }
}

object MapTest2{
  def main(args: Array[String]): Unit = {
    val map = Map("a" ->2)
    val maybe : Option[Int] = map.get("a")

    println(maybe.get)

    println(map.getOrElse("b",-1))
  }
}

object MapTest3{
  def main(args: Array[String]): Unit = {
    val map : mutable.Map[String,Int] = mutable.Map("a" ->1,"b"->2,"c"->3)

    val keys: Iterable[String] = map.keys
    val set: collection.Set[String] = map.keySet
    val iterator: Iterator[String] = map.keysIterator
    val values: Iterable[Int] = map.values

    println(keys)
    println(set)
    println(iterator)
  }
}





































































































