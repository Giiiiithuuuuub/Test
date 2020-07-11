package com.atguigu.spark.core.day06

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-09 14:40
 */
package object bean {

  case class HotCategory(
    categoryId: String,
    clickCount: Int,
    orderCount: Int,
    payCount: Int
  )

}
