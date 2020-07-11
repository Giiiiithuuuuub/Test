package com.atguigu.scala.day06

import java.util.Date

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-25 20:58
 */
class ApplyTest2 {

}

object ApplyTest2{
  def apply(): Date ={
    new java.util.Date()
  }
}

object Test{
  def main(args: Array[String]): Unit = {
    val applyTest = ApplyTest2()
    println(applyTest)//Mon May 25 20:59:44 CST 2020
  }
}

