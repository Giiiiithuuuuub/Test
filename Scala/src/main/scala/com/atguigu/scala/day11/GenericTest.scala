package com.atguigu.scala.day11

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-01 19:00
 */
object GenericTest {
  def main(args: Array[String]): Unit = {
    def f[Parent: Test](a: Parent):Unit = println(a)

    implicit val test: Test[User] = new Test
    f(new User)

    import math.sqrt

    sqrt(2)
  }
}

class Test[A] {
}

class Parent {
  val a : String = "3"
}

class User extends Parent {
}

class SubUser extends User {
}