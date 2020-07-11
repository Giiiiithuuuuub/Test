package com.atguigu.scala.day01

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-18 10:48
 */
object Scala_Test {

  def test() : String = {
    return "I am IronMan"
  }

  def test1() : Unit = {
    println(Scala_Test.test())
  }

  def main(args:Array[String]) : Unit={
    Scala_Test.test1()
  }

}
