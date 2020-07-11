package com.atguigu.spark.core.demandone.method1

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
    //2019-07-17_95_26070e87-1ad7-49a3-8fb3-cc741facaddf_48_2019-07-17 00:00:10_null_16_98_null_null_null_null_19

    val mapRDD: RDD[Array[String]] = initRDD
      .map(_.split("_"))


    val click : RDD[(String,Int)] = mapRDD
      .filter(_(6) != "-1")
      .map(x => (x(6), 1))
      .reduceByKey(_+_)

    val order : RDD[(String,Int)] = mapRDD
      .filter(_(8) != "null")
      .flatMap(
        x => {
          val strings : Array[String] = x(8).split(",")
          strings.toList.map(
            x =>{
              (x,1)
            }
          )
        }
      )
      .reduceByKey(_+_)

    val pay : RDD[(String,Int)] = mapRDD
      .filter(_(10) != "null")
      .flatMap(
        x => {
          val strings : Array[String] = x(10).split(",")
          strings.toList.map(
            x =>{
              (x,1)
            }
          )
        }
      )
      .reduceByKey(_+_)


    val temp: RDD[(String, ((Int, Int), Int))] = click.join(order).join(pay)

    val result : Array[(String,(Int,Int,Int))] = temp
      .mapValues{
        case((click,order),pay)=>{
          (click,order,pay)
        }
      }
      .sortBy(_._2,false)
      .take(10)

    result
  }
}
