package com.atguigu.scala.day10

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-30 21:13
 */
object PatternMathcTest {
  def main(args: Array[String]): Unit = {

    var a: Int = 10
    var b: Int = 20

    var temp: Int = 10

    var result = temp match {
      case 5 => "result is 5"
      case 10 => "10"
      case _ => "other"
    }

    println(result)
  }

}

object PatternMatchTest2 {
  def main(args: Array[String]): Unit = {

    def test(x: Int): String = x match {
      case 10 => "10"
      case 15 => "15"
      case 20 => "20"
      case _ => "other"

    }

    println(test(200))
  }
}

object PatternMatchTest3 {
  def main(args: Array[String]): Unit = {
    for (tuple <- Array((0, 1), (1, 0), (1, 1), (1, 0, 2))) {
      val result = tuple match {
        case (1,0,2) => "Test"
        case (0, _) => "0 ..." //是第一个元素是0的元组
        case (y, 0) => "" + y + "0" // 匹配后一个元素是0的对偶元组
        case (a, b) => "" + a + " " + b
        case _ => "something else" //默认
      }
      println(result)
    }

  }
}
