package com.atguigu.scala.day03

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-20 9:32
 */
object ForTest {

  def main(args: Array[String]): Unit = {
    for(i <- 1 to 18 by 2;j = (18 - i)/2){
      println(" " * j  + "*" * i)
    }
  }
}
