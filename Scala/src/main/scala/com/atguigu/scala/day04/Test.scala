package com.atguigu.scala.day04

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-22 15:28
 */
object Test {

  def main(args: Array[String]): Unit = {

    def test(f: =>String):Unit={
      println(f)
    }

    def fun():String = {
      "aaa"
    }

//    val a:=> String = fun
//    a()

    test(fun)
  }

}
