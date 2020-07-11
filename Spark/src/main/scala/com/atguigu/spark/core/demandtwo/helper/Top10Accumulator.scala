package com.atguigu.spark.core.demandtwo.helper

import com.atguigu.spark.core.demandtwo.Top10
import org.apache.spark.util.AccumulatorV2

import scala.collection.mutable

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-09 21:03
 */
class Top10Accumulator extends AccumulatorV2[(String,String),mutable.Map[String,Top10]]{

  private val accMap : mutable.Map[String,Top10] = mutable.Map[String,Top10]()

  override def isZero: Boolean = accMap.isEmpty

  override def copy() = new Top10Accumulator

  override def reset(): Unit = accMap.clear()

  override def add(v: (String, String)): Unit = {
    val instance : Top10 = accMap.getOrElse(v._1,Top10(v._1,0,0,0))

    v._2 match {
      case "click" => instance.click += 1
      case "order" => instance.order += 1
      case "pay" => instance.pay += 1
      case _ =>
    }

    accMap(v._1) = instance
  }

  override def merge(other: AccumulatorV2[(String, String), mutable.Map[String,Top10]]): Unit = {

    other.value.foreach(
      x=>{
        val temp: Top10 = accMap.getOrElse(x._1, Top10(x._1, 0, 0, 0))
        temp.click += x._2.click
        temp.order += x._2.order
        temp.pay += x._2.pay

        accMap(x._1) = temp
      }

    )

  }
  override def value: mutable.Map[String,Top10] = accMap
}
