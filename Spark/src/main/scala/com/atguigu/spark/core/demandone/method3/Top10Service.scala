package com.atguigu.spark.core.demandone.method3

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


    initRDD.flatMap(
      x=>{
        val strings : Array[String] = x.split("_")
        if (strings(6) != "-1"){
          List((strings(6),(1,0,0)))
        }else if(strings(8) != "null"){
          val datas : Array[String] = strings(8).split(",")
          datas.map(x => (x,(0,1,0)))
        }else if(strings(10) != "null"){
          val datas : Array[String] = strings(10).split(",")
          datas.map(x => (x,(0,0,1)))
        }else{
          List()
        }
      }
    ).reduceByKey{
      case((a,b,c),(d,e,f)) =>{
        (a+d,b+e,c+f)
      }
    }
      .sortBy(_._2,false)
      .take(10)

  }
}
