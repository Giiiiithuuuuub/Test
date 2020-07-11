package com.atguigu.spark.core.demandfour2

import com.atguigu.summer.core.DAO
import com.atguigu.summer.utils.EnvUtil
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-10 12:56
 */
class PageDAO extends DAO{
  override def readFile(path: String): RDD[UserVisitAction] = {
    val textRDD : RDD[String] = EnvUtil.getEnv().textFile(path)

    textRDD.map(
      x =>{
        val initDatas : Array[String] = x.split("_")
        UserVisitAction(
          initDatas(0),
          initDatas(1).toLong,
          initDatas(2),
          initDatas(3).toLong,
          initDatas(4),
          initDatas(5),
          initDatas(6).toLong,
          initDatas(7).toLong,
          initDatas(8),
          initDatas(9),
          initDatas(10),
          initDatas(11),
          initDatas(12).toLong
        )
      }
    )
  }
}
