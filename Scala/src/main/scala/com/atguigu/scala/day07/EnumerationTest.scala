package com.atguigu.scala.day07

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-26 18:24
 */
object EnumerationTest{

  def main(args: Array[String]): Unit = {
    println(Color.RED)//red
    println(Color.RED.id)//5
    println(Color.BLUE)//blue
    println(Color.BLUE.id)//0
    println(Color.YELLOW)//YELLOW
    println(Color.YELLOW.id)//6


  }

}
//Value方法有四个重载，分别是属性，id，属性+id，什么都没有。如果
object Color  extends Enumeration {
  val BLUE: Color.Value = Value("blue")//属性：如果不赋值id，那么放在最开始，则id为0
  val RED: Color.Value = Value(5,"red")//属性+id
  val YELLOW: Color.Value = Value//什么都没有，直接打印名称，放在显式id之后，则id自增
}
