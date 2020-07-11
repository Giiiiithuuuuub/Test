package com.atguigu.spark.core.day06.service

import com.atguigu.spark.core.day06.DAO.HotCategoryTop10AnalysisDAO
import com.atguigu.summer.core.Service
import org.apache.spark.rdd.RDD

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-09 10:38
 */
class HotCategoryTop10AnalysisService extends Service{

  private val hotCategoryTop10AnalysisDAO = new HotCategoryTop10AnalysisDAO

  override def analysis() = {
    //读取电商日志数据
    val action: RDD[String] = hotCategoryTop10AnalysisDAO.readFile("InputTest/user_visit_action.txt")

    val cacheRDD: action.type = action.cache()

    //对品类进行点击的统计
    //(category,clickCount)
    val clickRDD: RDD[(String, Int)] = action.map(
      x => {
        val datas = x.split("_")
        (datas(6), 1)
      }
    ).filter(_._1 != "-1")
    val categoryIDToClickCountRDD: RDD[(String, Int)] = clickRDD.reduceByKey(_ + _)

    //对品类进行下单的统计
    //(category,orderCount)
    val orderRDD: RDD[(String, Int)] = action.map(
      x => {
        val datas = x.split("_")
        datas(8)
      }
    ).filter(_ != "null")//这里是字符串的
      .flatMap{
        id =>{
          val ids = id.split(",")
          ids.map(id => (id,1))
        }
      }

    val categoryIdToOrderCountRDD: RDD[(String, Int)] = orderRDD.reduceByKey(_ + _)

    //对品类进行支付的统计
    //(category,payCount)
    val payRDD: RDD[(String, Int)] = action.map(
      x => {
        val datas = x.split("_")
        datas(10)
      }
    ).filter(_ != "null")//这里是字符串的
      .flatMap{
        id =>{
          val ids = id.split(",")
          ids.map(id => (id,1))
        }
      }

    val categoryIdToPayCountRDD: RDD[(String, Int)] = payRDD.reduceByKey(_ + _)


    //将上述统计结果转换为tuple，可以进行三者的连续比较
    //tuple => (元素1，元素2，元素3)
//    val joinRDD: RDD[(String, (Int, Int))] = categoryIDToClickCountRDD.join(categoryIdToOrderCountRDD)
//    val joinRDD1: RDD[(String, ((Int, Int), Int))] = joinRDD.join(categoryIdToPayCountRDD)
//    val mapRDD: RDD[(String, (Int, Int, Int))] = joinRDD1.mapValues {
//      case ((a, b), c) => {
//        (a, b, c)
//      }
//    }
    val newC: RDD[(String, (Int, Int, Int))] = categoryIDToClickCountRDD.map {
      case (id, a) =>
        (id, (a, 0, 0))
    }
    val newO: RDD[(String, (Int, Int, Int))] = categoryIdToOrderCountRDD.map {
      case (id, a) =>
        (id, (0, a, 0))
    }
    val newP: RDD[(String, (Int, Int, Int))] = categoryIdToPayCountRDD.map {
      case (id, a) =>
        (id, (0, 0, a))
    }

    val countRDD: RDD[(String, (Int, Int, Int))] = newC.union(newO).union(newP)

    val mapRDD: RDD[(String, (Int, Int, Int))] = countRDD.reduceByKey(
      (t1, t2) => {
        (t1._1 + t2._1, t1._2 + t2._2, t1._3 + t2._3)
      }
    )



    //将转换结构后的数据进行排序（降序）
    val sortRDD: RDD[(String, (Int, Int, Int))] = mapRDD.sortBy(_._2, false)

    //将排序后的结果取前10
    val result: Array[(String, (Int, Int, Int))] = sortRDD.take(10)
    result
  }
}
