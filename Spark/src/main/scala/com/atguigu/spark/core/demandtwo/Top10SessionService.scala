package com.atguigu.spark.core.demandtwo

import com.atguigu.spark.core.demandone.method5
import com.atguigu.summer.core.Service
import com.atguigu.summer.utils.EnvUtil
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-09 18:49
 */
class Top10SessionService extends Service {
  private val top10SessionDAO = new Top10SessionDAO

  override def analysis(data: Any) = {
    val top10: List[method5.Top10] = data.asInstanceOf[List[method5.Top10]]
    val list: List[String] = top10.map(_.categoryID)

    val bcList: Broadcast[List[String]] = EnvUtil.getEnv().broadcast(list)

    val initRDD: RDD[UserVisitAction] = top10SessionDAO.readFile("InputTest/user_visit_action.txt")

    initRDD.filter(
      x => {
        if (x.click_category_id != -1) {
          bcList.value.contains(x.click_category_id.toString)
//          var flag = false
//
//          top10.foreach(
//            temp => {
//              if (temp.categoryID.toLong == x.click_category_id) {
//                flag = true
//              }
//            }
//          )
//
//          flag
        } else {
          false
        }
      }
    )
      .map(
        x => {
          (x.click_category_id + "_" + x.session_id, 1)
        }
      )
      .reduceByKey(_ + _)
      .map(
        x => {
          val strings: Array[String] = x._1.split("_")
          (strings(0), (strings(1), x._2))
        }
      )
      .groupByKey()
      .mapValues(
        x => {
          x.toList.sortBy(-_._2).take(10)
        }
      )
      .collect()

  }


}
