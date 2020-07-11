package com.atguigu.scala.day05

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-23 14:21
 */
object Teat {
  def main(args: Array[String]): Unit = {
    val test = new test

    val name = test.name
    val age = test.age

    println(age)

    println(name)

    val nation = test.nation

    println(nation)
  }


}

class test{
  var name:String = _
  var age:Int = _

  val nation:String = "China"
}

