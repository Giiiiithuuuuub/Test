package com.atguigu.spark.core.demandone

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-09 20:25
 */
package object method5 {

  case class Top10(
                        categoryID: String,
                        var click: Int,
                        var order: Int,
                        var pay : Int
                      )

}
