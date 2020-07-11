package com.atguigu.scala.day08.ExerAll

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-28 14:41
 */
object Tuple {
  def main(args: Array[String]): Unit = {
    val tuple: (Int, String, List[Int]) = (1, "zhangsan", List(1, 2))

    println(tuple)
    println(tuple._2)
    println(tuple.productElement(1))
  }

}
