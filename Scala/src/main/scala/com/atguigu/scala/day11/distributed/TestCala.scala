package com.atguigu.scala.day11.distributed

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-01 20:58
 */
object TestCala {
  def main(args: Array[String]): Unit = {
    val start: Long = System.currentTimeMillis()
    val num = 10
    var sum = 0
    for (i <- 1 to num) {
      new Thread(
        new Runnable {
          override def run(): Unit = {
            sum = sum + i * 2
            Thread.sleep(i * 100) //模拟计算很慢的情况
          }
        }).start()

    }
    val end: Long = System.currentTimeMillis()

    println(
      s"""
         |计算结果为$sum,花费时间为${end - start}
         |""".stripMargin) //计算结果为110,花费时间为294
  }
}
