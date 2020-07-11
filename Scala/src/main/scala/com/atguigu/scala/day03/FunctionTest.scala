package com.atguigu.scala.day03

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-20 23:18
 */
object FunctionTest {

  def main(args: Array[String]): Unit = {

    def test(i: Int): Int = {
      return i
    }

    def test1(name: String, age: Int = 20): Unit = {
      println(s"name = $name , age = $age")

    }

    def test2(name: String = "aaa", age: Int): Unit = {
      println(s"name = $name , age = $age")
    }

    test2(age = 20)
  }

}

object FunctionTest2 {
  def main(args: Array[String]): Unit = {

    def test(): String = {
      return "I am IronMan"
    }

    //1. 可以省略return
    def test1(): String = {
      "I am IronMan"
    }

    //2. 可以省略大括号
    def test2(): String = "I am IronMan"

    //3. 省略返回值类型

    def test3() = "I am IronMan"

    //4. 省略括号

    def test4  = "I am IronMan"

    //调用
    test4 //如果声明没有括号,则调用时也不能带括号

    test3//如果生命时带括号,调用时可以加可以不加括号
    test()

    def test5 {return}

    //匿名函数
  }
}
