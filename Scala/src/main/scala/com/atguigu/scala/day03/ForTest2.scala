package com.atguigu.scala.day03

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-20 22:21
 */
object ForTest2 {

  def main(args: Array[String]): Unit = {

    //这是一个普通注释

    //TODO 这是需要完善的内容

    //FIXME 这个是错误代码

    //1.

    for (i <- 2 to 8 by 2) {
      //      println(i)
    }

    //2.

    for (i <- 2 until 8 by 2) {
      //      println(i)
    }

    //3.

    for (i <- Range(2, 8) by 2) {
      //      println(i)
    }

    for (i <- Range(2, 8, 2)) {
      //      println(i)
    }

    //4.

    for (i <- Range(8,1) if i != 3) {
//            println(i)
    }
    //5.

    for (i <- 2 until 8 by 2; j <- Range(2, 5)) {
      //      println("i = " + i + "j = " + j)
    }

    //6.

    for (i <- 2 to 8 by 2; j = i + 1) {
      //      println(j)
    }

    //TODO 只使用一次for循环实现九层塔

    for (i <- 1 to 18 by 2; j = (18 - i)/2) {
//      print(" " * j + "*" * i)
//      println()
    }

//    for(i <- 10 to 1 by -2){
//      println(i)
//    }
//
//    for (i <- 10 to 1 by -1){
//      println(i)
//    }
//
//    for(i <- Range(10,1,-1)){
//      println(i)
//    }

    for (i <- 1 to 10 by -1){//这个是错误的
//      println(i)
    }


    val result = for(i <- Range(10,1,-1)) yield {
      i
    }
    println(result)
  }



}
