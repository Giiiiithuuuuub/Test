package com.atguigu.scala.day04

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-23 1:25
 */
object Test3 {
  def main(args: Array[String]): Unit = {


    var i = 2

    val inc:()=>Unit = ()=>i=i + 1

    println(inc)
  }

}
