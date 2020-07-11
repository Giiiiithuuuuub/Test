package com.atguigu.scala.day03

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-21 0:24
 */
object HomeWork {

  def main(args: Array[String]): Unit = {

    //第一题
    def toDouble(i : Int)  = i * 2

    println(toDouble(20))

    //第二题
    def toAny(f: Int => Any) = f(10)//假设数字A为10

    println(toAny( "*" * _ ))

    //第三题
    def test(i : Int ,j : Int , c : (Int,Int) => Int ): Unit ={
      //这个题没看懂
    }



    //第四题
    def myFilter(word : String): Any = {

      if (word == null) return

      val strings = word.split(" ")

//      for (i <- 0 until strings.length){
//
//        if (strings(i).startsWith("s")){
//          print(strings(i) + " ")
//        }
//      }

      for (i <- strings){
        if (i.startsWith("s")){
          println(i)
        }
      }
    }

    //调用
    myFilter("hello world spark scala")
  }

}
