package com.atguigu.scala.day05

import java.util


/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-23 21:42
 */
object ImportTest {
  def main(args: Array[String]): Unit = {
    //1. 在任何位置声明：为了开发方便
    import com.atguigu.scala
    //2. 倒包:注意是倒包，而不是类，没什么O用
    import java.util
    new util.ArrayList()
    //3. 同时倒多个类:作用：简化开发
    import java.util.{List, ArrayList}
    //4. 屏蔽某个包:防止冲突，因为冲突的话导致程序无法执行
    import java.util._
    import java.sql.{Date=>_,Array=>_,_}
    //5. 起别名:方便使用
    import java.util.{ArrayList=>MyArrayList}
    new MyArrayList[String]()
    //6. 绝对路径导包:防止双亲委派失效
    import _root_.java.util.ArrayList
    //7. 默认倒的包:系统自己导入
    import java.lang._
    import scala._
    import Predef._
    //8. 用下划线代替星号
    //9.可以导入val生命的对象
//    val forImport = new ForImport
//    import forImport._

  }



//
//  def test()={
//    new util.ArrayList[String]()
//    new util.HashMap[String]()
//  }
//
//  class ForImport{
//
//  }
}
