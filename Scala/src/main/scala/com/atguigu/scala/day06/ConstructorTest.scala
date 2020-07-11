package com.atguigu.scala.day06

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-25 16:08
 */
class ConstructorTest(name:String) {

  println("-------")
  def this()={
    this(name)
  }

}

object test{
//def main(args: Array[String]): Unit = {
//    val constructorTest = new ConstructorTest()
//
//    val zhangsan = new ConstructorTest("zhangsan")
//  }
}
