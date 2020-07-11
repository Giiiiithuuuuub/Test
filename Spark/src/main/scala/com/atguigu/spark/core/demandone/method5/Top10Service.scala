package com.atguigu.spark.core.demandone.method5

import com.atguigu.spark.core.demandone.method5.helper.Top10Accumulator
import com.atguigu.summer.core.Service
import com.atguigu.summer.utils.EnvUtil
import org.apache.spark.rdd.RDD

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-09 18:49
 */
class Top10Service extends Service {
  private val top10DAO = new Top10DAO

  override def analysis() = {
    val initRDD: RDD[String] = top10DAO.readFile("InputTest/user_visit_action.txt")

    val accumulator = new Top10Accumulator

    EnvUtil.getEnv().register(accumulator)

    initRDD
        .foreach(
          x =>{
            val datas : Array[String] = x.split("_")
            if (datas(6) != "-1"){
              accumulator.add(datas(6),"click")
            }else if(datas(8) != "null"){
              val strings : Array[String] = datas(8).split(",")
              strings.foreach(
                x =>{
                  accumulator.add(x,"order")
                }
              )
            }else if(datas(10) != "null") {
              val strings: Array[String] = datas(10).split(",")
              strings.foreach(
                x => {
                  accumulator.add(x, "pay")
                }
              )
            }else{
              Nil
            }
          }
        )

    accumulator
      .value
      .values
        .toList
        .sortWith{
          (left,right)=>{
            if(left.click > right.click){
              true
            }else if(left.click == right.click){
              if(left.order > right.order){
                true
              }else if(left.order == right.order){
                left.pay >= right.pay
              }else{
                false
              }
            }else{
              false
            }
          }
        }
      .take(10)

  }
}
