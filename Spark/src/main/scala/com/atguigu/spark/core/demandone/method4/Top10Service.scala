package com.atguigu.spark.core.demandone.method4

import com.atguigu.summer.core.Service
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


    val temp: RDD[(String, Top10Bean)] = initRDD.flatMap(
      x => {
        val strings: Array[String] = x.split("_")
        if (strings(6) != "-1") {
          List((strings(6), Top10Bean(strings(6), 1, 0, 0)))
        } else if (strings(8) != "null") {
          val datas: Array[String] = strings(8).split(",")
          datas.map(x => (x, Top10Bean(x, 0, 1, 0)))
        } else if (strings(10) != "null") {
          val datas: Array[String] = strings(10).split(",")
          datas.map(x => (x, Top10Bean(x, 0, 0, 1)))
        } else {
          Nil
        }
      }
    )

    val reduceRDD : RDD[(String,Top10Bean)] = temp.reduceByKey(
      (x, y) => {
        x.click += y.click
        x.order += y.order
        x.pay += y.pay
        x
      }
    )
    //此时没法用sortBy，所以换个方式
    //能collect到内存的前提就是数据量没有很大
    reduceRDD
      .collect()
      .sortWith{
        (left,right)=>{
          if(left._2.click > right._2.click){
            true
          }else if(left._2.click == right._2.click){
            if(left._2.order > right._2.order){
              true
            }else if(left._2.order == right._2.order){
              left._2.pay >= right._2.pay
            }else{
              false
            }
          }else{
            false
          }
        }
      }.take(10)
  }
}
