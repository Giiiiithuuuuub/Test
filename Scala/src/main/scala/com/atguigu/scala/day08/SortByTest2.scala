package com.atguigu.scala.day08

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-27 21:17
 */
object SortByTest2 {

  def main(args: Array[String]): Unit = {
    val user1 = new User()
    user1.age = 20
    user1.name = "zhangsan"

    val user2 = new User()
    user2.name = "lisi"
    user2.age = 20

    val list = List(user2, user1 )

    println(list.sortBy(user => user.age))

  }
  class User {
    var name : String = _
    var age : Int = _

    override def toString: String = {
      s"User[$name, $age]"
    }
  }


}
