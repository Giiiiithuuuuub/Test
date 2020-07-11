package com.atguigu.scala.day02

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-19 21:15
 */
object IfTest {
  def main(args: Array[String]): Unit = {
    val color = "red"
    val daan = if (color == "red") {
      "钢铁侠"
    } else if (color == "green") {
      "绿巨人"
    } else if (color == "blue") {
      "美国队长"
    } else {
      "Somebody"
    }

    println(daan)
  }
}

object ForTest {
  def main(args: Array[String]): Unit = {

    for (i <- 3 to 9 by 2){
      println(i)
    }

    *-* //这是一个方法


  }

  def *-*():Unit={
    val temp = 10;
    for (i <- Range(2,10,2)){
      println(
        s"""
           |$i + $temp
           |""".stripMargin)
    }
  }

  def &*-*():Unit={

  }

}

object ForTest10{
  def main(args: Array[String]): Unit = {

    def test(name:String)="zhangsan"

  }
}

trait Test111{
  val color : String = "red"
  def thisistest1(name :String)
}
