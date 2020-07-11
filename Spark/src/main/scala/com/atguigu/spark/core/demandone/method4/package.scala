package com.atguigu.spark.core.demandone

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-09 20:25
 */
package object method4 {

  case class Top10Bean(
                        categoryID: String,
                        var click: Int,
                        var order: Int,
                        var pay : Int
                      )

}
