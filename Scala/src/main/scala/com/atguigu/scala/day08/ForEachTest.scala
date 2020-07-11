package com.atguigu.scala.day08

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-27 9:07
 */
object ForEachTest {

  def main(args: Array[String]): Unit = {

//    val set1 = Set(1,2,3,4)
//    val set2 = Set(5,6,7,8)
//
//    // 增加数据
//    val set3: Set[Int] = set1 + 5 + 6
//    val set4: Set[Int] = set1.+(6,7,8)
//    println( set1 eq set3 ) // false
//    println( set1 eq set4 ) // false
//    set4.foreach(println)
//    // 删除数据
//    val set5: Set[Int] = set1 - 2 - 3
//    set5.foreach(println)
//
//    val set6: Set[Int] = set1 ++ set2
//    set6.foreach(println)
//    println("********")
//    val set7: Set[Int] = set1.++:()
//    set7.foreach(println)
//    println(set6 eq set7)

    val tuple: (Int, String, Int) = (1,"zhangsan",30)
    println(tuple)
//
//    val kv: (String, Int) = ("a", 1)
//    val kv1: (String, Int) = "a" -> 1
//    println( kv eq kv1 )

//    val stringToInt = Map(("a", 1), ("b", 2))
//
//    for ( kv <- stringToInt){
//      println(kv._1)
//    }

    val list = List(1,3,5)

    list.size
    list.length

  }


}
