package com.atguigu.spark.core.demandone.method2

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


    val mapRDD: RDD[Array[String]] = initRDD
      .map(_.split("_"))

    //缓存到内存中，避免每次进行文件读取和进行切分
    val cacheRDD: RDD[Array[String]] = mapRDD.cache()

    val click: RDD[(String, Int)] = cacheRDD
      .filter(_ (6) != "-1")
      .map(x => (x(6), 1))
      .reduceByKey(_ + _)

    val order: RDD[(String, Int)] = cacheRDD
      .filter(_ (8) != "null")
      .flatMap(
        x => {
          val strings: Array[String] = x(8).split(",")
          strings.toList.map(
            x => {
              (x, 1)
            }
          )
        }
      )
      .reduceByKey(_ + _)

    val pay: RDD[(String, Int)] = cacheRDD
      .filter(_ (10) != "null")
      .flatMap(
        x => {
          val strings: Array[String] = x(10).split(",")
          strings.toList.map(
            x => {
              (x, 1)
            }
          )
        }
      )
      .reduceByKey(_ + _)


    //由于join需要shuffle而且容易笛卡尔积，所以最好不要使用这种方式，这里替换为reduceByKey
    //    val temp: RDD[(String, ((Int, Int), Int))] = click.join(order).join(pay)

    val clickChange : RDD[(String,(Int,Int,Int))] = click.map(x =>{
      (x._1,(x._2,0,0))
    })
    val orderChange : RDD[(String,(Int,Int,Int))] = order.map(x =>{
      (x._1,(0,x._2,0))
    })
    val payChange : RDD[(String,(Int,Int,Int))] = pay.map(x =>{
      (x._1,(0,0,x._2))
    })
    val temp: RDD[(String,(Int,Int,Int))] = clickChange.union(orderChange).union(payChange)

    val result : Array[(String,(Int,Int,Int))] = temp.reduceByKey(
      (x,y)=>{
        (x._1 + y._1,x._2 + y._2,x._3 + y._3)
      }
    )
      .sortBy(_._2,false)
      .take(10)
    result

  }
}
