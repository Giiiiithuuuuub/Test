package com.atguigu.scala.day06

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-25 20:18
 */
//class Apply {
//  var name : String = _
//  //假设某个类的构造器私有，那么它无法使用new的方式创建:举例
//  private def this(name: String) {
//    this()
//
//    this.name = name
//  }
//}
//
//object Apply {
//  //注意：伴生对象可以访问伴生类的私有属性和方法
//  def apply: Apply = {
//    //所以，在伴生对象中，可以使用这个对象的私有构造器来new对象
//    new Apply("zhangsan")
//  }
//}
//
//object ApplyTest {
//  def main(args: Array[String]): Unit = {
//    //    new Apply("zhangsan")//如果这样直接new对象，是会报错的
//    val theInstance = Apply.apply
//    //由于scala会自动识别apply方法，所以方法名可省略，简写为：
//    val otherInstance = Apply
//
//    //可以调用属性，显然成功创建实例
//    println(theInstance.name)//zhangsan
//    println(otherInstance)//zhangsna
//
//  }
//}
