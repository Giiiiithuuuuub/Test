package com.atguigu.scala.day02

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-19 21:05
 */
object StringTest {
  def main(args : Array[String]) : Unit = {
    val s : Int = 10

    val *|* : Int = s.+(10)

    val &^& : Int = *|* + (10)

    val k = &^& + 10


  }

}

object StringTest2{
  def main(args : Array[String]) : Unit = {
    val name = "zhangsan"
    val age  = 20

    println("name :" + name + "age :" + age)

    printf("name : %s , age : %d",name,age)

    println(s"name : $name , age : $age")

    println(
      s"""
         |name : $name , age : $age
         |""".stripMargin)
  }
}
