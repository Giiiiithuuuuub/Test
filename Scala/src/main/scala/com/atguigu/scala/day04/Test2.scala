package com.atguigu.scala.day04

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-22 22:53
 */
object Test2 {

  def main(args: Array[String]): Unit = {

    //    def test(i : Int)={
    //      def sum(j : Int): Unit ={
    //        println((i + j))
    //      }
    //
    //      sum _
    //    }
    //
    //    test(10)(20)


    //    def test(f: => Int)={
    //      f
    //    }
    //
    //    val a = test{
    //      def test2(x:Int)(y:Int)(f:(Int,Int)=>Int)={
    //        f(x,y)
    //      }
    //
    //      test2(10)(20)(_ + _)
    //    }
    //
    //    println(a)
    //
    //    //能直接打印
    //    println({
    //      def test2(x: Int)(y: Int)(f: (Int, Int) => Int) = {
    //        f(x, y)
    //      }
    //
    //      test2(10)(20)(_ + _)
    //    })

//    //常规递归
//    def test(num: Int): Int = {
//      if (num <= 1) {
//        num
//      } else {
//        num + test(num - 1)
//      }
//    }
//
//    println(test(5))
//
    //尾递归

    def test2(num: Int, result: Int): Int = {
      if (num <= 1) {
        result
      } else {
        test2(num - 1, num - 1 + result)
      }
    }

    println(test2(5, 5))

    def test3={
      println("aaa")
    }

    lazy val a = test3

    println("---------")
    a


  }


}
