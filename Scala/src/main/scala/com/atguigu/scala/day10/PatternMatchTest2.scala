package com.atguigu.scala.day10

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-30 22:03
 */
object PatternMatchTest4 {
  def main(args: Array[String]): Unit = {
    val user:User = User("zhangsan",20)
    val result = user match {
      case User("zhangsan",20) =>"yes"
    }

    println(result)
  }
}

//class User(val name : String,val age : Int){}
//object User{
//  def apply(name: String, age: Int): User = new User(name, age)
//
//  def unapply(arg: User): Option[(String, Int)] = {
//    if (arg == null){
//      None
//    }else{
//      Some(arg.name,arg.age)
//    }
//  }
//}

