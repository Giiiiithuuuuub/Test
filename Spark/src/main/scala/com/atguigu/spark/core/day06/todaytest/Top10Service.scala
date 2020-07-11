package com.atguigu.spark.core.day06.todaytest

import com.atguigu.summer.core.Service
import org.apache.spark.rdd.RDD

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-09 14:03
 */
class Top10Service extends Service{
  private val top10DAO = new Top10DAO

  override def analysis() = {
    val initRDD: RDD[String] = top10DAO.readFile("InputTest/user_visit_action.txt")

    val tuples: Array[(String, (Int, Int, Int))] = initRDD.flatMap(
      x => {
        val strings: Array[String] = x.split("_")
        if (strings(6) != "-1") {
          List((strings(6), (1, 0, 0)))
        } else if (strings(8) != "null") {
          val ids = strings(8).split(",")
          ids.map(id => (id, (0, 1, 0)))
        } else if (strings(10) != "null") {

          val ids = strings(10).split(",")
          ids.map(id => (id, (0, 0, 1)))
        } else {
          Nil
        }
      }
    )
      .reduceByKey(
        (a, b) => (a._1 + b._1, a._2 + b._2, a._3 + b._3)
      )
      .sortBy(_._2, false)
      .take(10)
    tuples
  }
}
