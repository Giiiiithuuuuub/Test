package com.atguigu.spark.core.demandfour2

import java.text.{DecimalFormat, NumberFormat}

import com.atguigu.summer.core.Service
import org.apache.spark.rdd.RDD

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-10 13:03
 */
class PageService extends Service {
  private val pageDAO = new PageDAO

  override def analysis(): Any = {
    val initRDD: RDD[UserVisitAction] = pageDAO.readFile("InputTest/user_visit_action.txt")
    initRDD.cache()

    val initList : List[Int] = List(1,2,3,4,5,6,7)
    val formattedList : List[(Int,Int)] = initList.zip(initList.tail)

    val denominator : Array[(Long,Int)] = initRDD
      .filter(
        x=>{
          initList.init.contains(x.page_id.toInt)
        }
      )
      .map(x => (x.page_id,1))
      .reduceByKey(_+_)
      .collect

    val numerator: Array[(String, Int)] = initRDD
      .groupBy(_.session_id)
      .mapValues(
        line => {
          val tempChange: List[Long] = line
            .toList
            .sortBy(_.action_time)
            .map(_.page_id)
          val list: List[(Long, Long)] = tempChange.zip(tempChange.tail)

          list
            .filter(
              x => formattedList.contains(x)
            )
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

    numerator.foreach(
      x=> println(x._1 + "的什么几把玩意儿比率为：" + (x._2.toDouble/denominator.toMap.getOrElse(x._1.split("-")(0).toInt,1)).formatted("%.3f"))
    )

  }

}

object TestSlide{
  def main(args: Array[String]): Unit = {
    val ints = List(1, 2, 3, 4, 5, 6, 7)

    val iterator: Iterator[List[Int]] = ints.sliding(2)

    val flatten: List[Int] = iterator
      .toList
      .flatten

    flatten.foreach(println)

    ints.union(iterator.toList)
  }
}
