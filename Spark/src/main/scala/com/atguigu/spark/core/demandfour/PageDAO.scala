package com.atguigu.spark.core.demandfour

import com.atguigu.summer.core.DAO
import com.atguigu.summer.utils.EnvUtil
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-09 18:48
 */
class PageDAO extends DAO{
  override def readFile(path: String): RDD[UserVisitAction] = {
    val sparkContext : SparkContext = EnvUtil.getEnv()
    val textRDD: RDD[String] = sparkContext.textFile(path)
    val value: RDD[UserVisitAction] = textRDD
      .map {
        x => {
          val datas: Array[String] = x.split("_")
          UserVisitAction(
            datas(0),
            datas(1).toLong,
            datas(2),
            datas(3).toLong,
            datas(4),
            datas(5),
            datas(6).toLong,
            datas(7).toLong,
            datas(8),
            datas(9),
            datas(10),
            datas(11),
            datas(12).toLong
          )

        }
      }
    value
  }
}
