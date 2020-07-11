package com.atguigu.scala.day08

import scala.collection.mutable

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-29 17:58
 */
object HelloTest4 {
  def main(args: Array[String]): Unit = {
    val list: List[(String, Int)] = List(
      ("hello", 4),
      ("hello spark", 3),
      ("hello spark scala", 2),
      ("hello spark scala hive", 1)
    )

    list.map(x => (x._1 + " ") * x._2)
      .flatMap(_.split(" "))
      .groupBy(x => x)
      .toList
      .map(x => (x._1, x._2.size))
      .sortBy(_._2)(Ordering.Int.reverse)
      .take(3)
      .foreach(println)

  }
}

object HelloTest5{
  def main(args: Array[String]): Unit = {
    val list: List[(String, Int)] = List(
      ("hello", 4),
      ("hello spark", 3),
      ("hello spark scala", 2),
      ("hello spark scala hive", 1)
    )

    list.flatMap( x => x._1.split(" ").map(y => (y,x._2)))
      .groupBy(_._1)
      .toList
      .map(x=> (x._1,x._2.map(y => y._2).sum))
      .sortBy(_._2)(Ordering.Int.reverse)
      .take(3)
      .foreach(println)
  }
}

object HelloTest6{
  def main(args: Array[String]): Unit = {
    val list: List[(String, Int)] = List(
      ("hello", 4),
      ("hello spark", 3),
      ("hello spark scala", 2),
      ("hello spark scala hive", 1)
    )

    list.flatMap(x => x._1.split(" ").map(y => (y,x._2)))
      .groupBy(_._1)
      .mapValues(x => x.map(y => y._2).sum)
      .toList
      .sortBy(-_._2)
      .take(3)
      .foreach(println)

  }
}

object TupleTest{
  def main(args: Array[String]): Unit = {
    val list : List[(Int,String)] = List((30,"zhangsan"),(20,"lisi"),(40,"wangwu"))

//    list.sortBy(t => t)(Ordering.Tuple2(Ordering.Int.reverse,Ordering.String.reverse))
//      .foreach(println)

    println(list.sortWith(
      (left, right) => {
        if (left._1 > right._1) {
          true
        } else if (left._1 == right._1) {
          left._2 > right._2
        } else{
          false
        }
      }
    ))

//    list.sortWith()
  }
}

object FoldTest{
  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4,5)

//    val str: String = list.fold( )(_ + _)
//
//   println(str)

    println(list.foldRight(10)(_ - _))
  }
}

object FoldTest2{
  def main(args: Array[String]): Unit = {
    val map1 : mutable.Map[String,Int] = mutable.Map("a"->1,"b"->2,"c"->3)
    val map2 : mutable.Map[String,Int] = mutable.Map("a"->4,"d"->5,"c"->6)

    map1.foldLeft(map2)(
      (x , y) => {
        x.update(y._1,x.getOrElse(y._1,0)+ y._2)
        x
      }
    ).foreach(println)
  }
}

object HomeWorkTest{
  def main(args: Array[String]): Unit = {
//    val list: List[(String, Int)] = List(
//      ("hello", 4),
//      ("hello spark", 3),
//      ("hello spark scala", 2),
//      ("hello spark scala hive", 1)
//    )
//
//    val list1: List[Map[String, Int]] = list.map(x => x._1.split(" ").map(y => (y, x._2)).toMap)
//
//    for(i <- 0 to list1.length - 2){
//      list1(i).foldLeft(list1(i + 1))(
//        (x,y) => {
//          x(y._1) = x.getOrElse(y._1,0) + y._2
//          x
//        }
//      )
//    }
//
//    println(list1)

  }
}
