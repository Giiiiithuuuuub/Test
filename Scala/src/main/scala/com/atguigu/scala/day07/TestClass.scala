package com.atguigu.scala.day07

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-26 20:52
 */
class TestClass private {

  private var name : String = "zhangsan"

}

object TestClass{
  private val clazz = new TestClass
  clazz.name = "lisi"

  def apply():TestClass={
    clazz
  }
}

object xxxxxx{
  def main(args: Array[String]): Unit = {
    val clazz: TestClass = TestClass.apply()

  }
}
