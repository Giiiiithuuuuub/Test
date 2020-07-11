package com.atguigu.spark.streaming.day03

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Duration, Durations, Seconds, StreamingContext}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-14 1:37
 */
object StreamingWC {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("wc")

    val ssc = new StreamingContext(sparkConf,Durations.milliseconds(3000))

    val receiver: ReceiverInputDStream[String] = ssc.socketTextStream("localhost", 9999)

    receiver
      .flatMap(_.split(" "))
      .map((_,1))
      .reduceByKey(_+_)
      .print()

    ssc.start()

    ssc.awaitTermination()

  }
}
