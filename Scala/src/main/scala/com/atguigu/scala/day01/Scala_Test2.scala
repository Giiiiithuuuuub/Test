package com.atguigu.scala.day01

import scala.io.{Source, StdIn}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-19 1:40
 */
object Scala_Test2 {

  def main(args: Array[String]): Unit = {
    //这是一个单行注释
    val name : String = "IronMan"
    var age = 40

    println(
      s"""
        |name = $name , age = $age
        |""".stripMargin)

    println("name = " + name + ",age = " + age)

    printf("name = %s , age = %d\n",name,age)

    println(s"name = $name,age = $age")

    val temp = StdIn.readInt()

    println(temp)

    for(i <- 1 to 5){
      println(i)
    }

  }

}
