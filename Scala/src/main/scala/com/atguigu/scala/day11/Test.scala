package com.atguigu.scala.day11

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-01 20:05
 */
object TestABCD {
  def main(args: Array[String]): Unit = {
    def getGoodsPrice(goods:String) = {
      val prices = Map("book" -> 5, "pen" -> 2, "sticker" -> 1)
      prices.getOrElse(goods, 0)
    }

    getGoodsPrice("pen")
  }
}
