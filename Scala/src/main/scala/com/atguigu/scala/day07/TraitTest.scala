package com.atguigu.scala.day07

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-26 12:05
 */
object TraitTest {
  def main(args: Array[String]): Unit = {
    val temp = new MySQL37 with Opearate37
    temp.operData()
  }

}

trait Opearate37 {
  def operData(): Unit = {
    println("操作数据")
  }
}

trait DB37 extends Opearate37 {
  override def operData(): Unit = {
    print("向数据库中")
    super.operData()
  }
}

trait Log37 extends Opearate37 {
  override def operData(): Unit = {
    print("向日志中")
    super[Opearate37].operData()
  }
}

class MySQL37 extends DB37 with Log37 {

}
