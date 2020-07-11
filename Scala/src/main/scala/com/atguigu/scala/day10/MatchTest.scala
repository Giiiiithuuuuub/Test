package com.atguigu.scala.day10

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-30 9:37
 */
object MatchTest {
  def main(args: Array[String]): Unit = {
    val user: User = User("zhangsan", 11)
    val result = user match {
      case User("zhangsan", 11) => "yes"
      case _ => "no"
    }

  }

}


class User(val name: String, val age: Int)
object User{
  def apply(name: String, age: Int): User = new User(name, age)
  def unapply(user: User): Option[(String, Int)] = {
    if (user == null)
      None
    else
      Option(user.name, user.age)
  }
}

class ZhenFeijin{

}


