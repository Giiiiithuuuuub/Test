package com.atguigu.scala.day03

import scala.util.control.Breaks._

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-20 23:10
 */
object WhileTest {

  def main(args: Array[String]): Unit = {

    var i = 0

    breakable(

      while (i < 10){

        if (i == 8){
          break
        }
        println(i)
        i += 1
      }
    )
  }

}
