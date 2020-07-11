package com.atguigu.scala.day07

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-26 10:20
 */
abstract class AbstractTest {
  var name:String

  val age:Int = 20

  def test(): Unit ={
//    age = 30
    println(age)
  }
}

class aaa extends AbstractTest{
  var name:String = "zhangsan"

  override val age = 30

}

object xxx{
  def main(args: Array[String]): Unit = {
    val aaa = new aaa

    aaa.test()
  }
}
