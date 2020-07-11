package com.atguigu.scala.day10

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-30 22:16
 */
object MatchTest2 {
  def main(args: Array[String]): Unit = {
    val ironMan : IronMan = new IronMan("IronMan",40)

    val result = ironMan match {
      case IronMan("IronMan",30) => "yse"
      case IronMan("IronMan",40) => "right"

    }

    println(result)
  }
}

case class IronMan(name : String,age : Int)
