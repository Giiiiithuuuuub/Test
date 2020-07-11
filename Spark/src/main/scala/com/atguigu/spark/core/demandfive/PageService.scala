package com.atguigu.spark.core.demandfive

import java.time.format.DateTimeFormatter
import java.time.{Duration, LocalDateTime}

import com.atguigu.summer.core.Service
import org.apache.spark.rdd.RDD

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-10 13:03
 */
class PageService extends Service {
  private val pageDAO = new PageDAO

  override def analysis() = {
    val initRDD: RDD[UserVisitAction] = pageDAO.readFile("InputTest/user_visit_action.txt")
    initRDD.cache()

//    //计算1-10号页面，页面单跳平均时间
//
//    val initList: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//    val formattedList: List[(Int,Int)] = initList.zip(initList.tail)
//    val determinedList: List[String] = formattedList.map(x => (x._1 + "-" + x._2)) //((1-2),(2-3)...)

    //计算页面单跳的数量
    val denominator: Array[(String, Int)] = initRDD
      .groupBy(_.session_id)
      .mapValues(
        line => {
          val tempChange: List[Long] = line
            .toList
            .sortBy(_.action_time)
            .map(_.page_id)
          val list: List[(Long, Long)] = tempChange.zip(tempChange.tail)

          list
//            .filter(
//              x => formattedList.contains(x)
//            )
            .map {
              case (a, b) => {
                (a + "-" + b, 1)
              }
            }


        }
      )
      .map(_._2)
      .flatMap(x => x)
      .reduceByKey(_ + _)
      .collect()

    //计算页面单跳的时间

    val numerator: Array[(String, Long)] = initRDD
      .groupBy(_.session_id)
      .mapValues(
        line => {
          val temp: List[(Long, String)] = line
            .toList
            .sortBy(_.action_time)
            .map(
              x => {
                (x.page_id, x.action_time)
              }
            )
          val tempChange: List[((Long, String), (Long, String))] = temp.zip(temp.tail)
          tempChange
            .map {
              case ((a, b), (c, d)) =>
                (a + "-" + c, TimeUtils.getTimeGap(b,d))
            }
//            .filter(
//              x => (determinedList.contains(x._1))
//            )

        }
      )
      .map(_._2)
      .flatMap(x => x)
      .reduceByKey(_ + _)
      .collect()

    numerator.foreach(
      x => {
        println("页面" + x._1+  " 平均时间"  + (x._2.toDouble/denominator.toMap.getOrElse(x._1,1)).formatted("%.3f"))
      }
    )


  }


}



