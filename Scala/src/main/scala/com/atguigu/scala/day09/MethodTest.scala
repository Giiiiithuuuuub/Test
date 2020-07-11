package com.atguigu.scala.day09

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-29 10:47
 */
object MethodTest {
  def main(args: Array[String]): Unit = {
    val list1 = List(1,2,3,4,7)
    val list2 = List(3,4,5,6)

    val tuples: List[(Int, Int)] = list1.zip(list2)

    val unzip: (List[Int], List[Int]) = tuples.unzip

    println(unzip)

//    list1.reduce()
//    list1.reduceLeft()
//    list1.reduceRight()

//    list1.foldLeft()





  }

}

object MethodTest2{
  def main(args: Array[String]): Unit = {
    val doubles = List(1.1, 1.2, 1.3)

//    val i: Int = doubles.foldLeft(1)(_ + _)
  }
}
