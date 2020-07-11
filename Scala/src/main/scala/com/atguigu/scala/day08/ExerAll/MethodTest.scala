package com.atguigu.scala.day08.ExerAll

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-28 14:49
 */
object MethodTest {
  def main(args: Array[String]): Unit = {
    val list = List(2,4,5,6,7)

//    val ints: List[Int] = list.take(2)
//
//    val list2 : List[Int] = list.takeRight(3)
//
//    println(ints)
//    println(list2)

    val maybeInt: Option[Int] = list.find(_ % 2 != 0)
    println(maybeInt)

  }
}

object MethodTest2{
  def main(args: Array[String]): Unit = {
    val list : List[Int] = List(1,2,3,4)
    val ints: List[Int] = list.take(1)
    val head: Int = list.head
    println(head)
    println(ints)

    val inits: Iterator[List[Int]] = list.inits

  }
}

object MethodTest3{
  def main(args : Array[String]):Unit={
    val list1 = List(1,2,3,4,5)
    val list2 = List(8,9,10)

//    val list3 : List[Int] = list1.union(list2)
//    println(list3)

//    val lsit3 = list1.diff(list2)
//    println(lsit3)

//    val tuple: (List[Int], List[Int]) = list1.splitAt(5)
//
//    println(tuple._1)
//    println(tuple._2)

//    val iterator: Iterator[List[Int]] = list1.sliding(2)
//    while (iterator.hasNext){
//      println(iterator.next())
//    }

    val iterator: Iterator[List[Int]] = list1.sliding(2, 2)
    while (iterator.hasNext){
      println(iterator.next())
    }

    val tuples: List[(Int, Int)] = list1.zip(list2)
    println(tuples)

    val index: List[(Int, Int)] = list1.zipWithIndex
    println(index)

  }
}

object MethodTest4{
  def main(args: Array[String]): Unit = {
    val list = List(9,4,10,3,8,9,7)
//    val product: Int = list.product
//    println(product)

    val ints: List[Int] = list.sortWith(_ < _)
    println(ints)
    val list2 = list.sortBy(x => x)
    println(list2)
  }
}

object MethodTest5{
  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4,5)

//    val i: Int = list.reduce(_*2+_)
//    println(i)

    val i: Int = list.reduceLeft(_ + _)
    println(i)
  }
}















































