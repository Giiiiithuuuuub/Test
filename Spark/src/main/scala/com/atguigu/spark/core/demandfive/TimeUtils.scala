package com.atguigu.spark.core.demandfive

import java.time.{Duration, LocalDateTime}
import java.time.format.DateTimeFormatter

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-10 21:41
 */
case object TimeUtils{
  //封装获得两个字符串的时间差
  def getTimeGap(a : String, b: String): Long ={
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    val timeA: LocalDateTime = LocalDateTime.parse(a, formatter)
    val timeB : LocalDateTime = LocalDateTime.parse(b,formatter)

    val duration: Duration = Duration.between(timeA, timeB)
    duration.getSeconds
  }
}
