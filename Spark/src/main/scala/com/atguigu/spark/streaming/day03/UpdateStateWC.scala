package com.atguigu.spark.streaming.day03

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Durations, StreamingContext}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-14 1:37
 */
object UpdateStateWC {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("wc")

    val ssc = new StreamingContext(sparkConf,Durations.milliseconds(3000))
    
    ssc.sparkContext.setCheckpointDir("output")
    ssc.checkpoint("output")
    val receiver: ReceiverInputDStream[String] = ssc.socketTextStream("localhost", 9999)

    receiver
      .flatMap(_.split(" "))
      .map((_,1L))
      .updateStateByKey[Long](
        (seq:Seq[Long],buffer:Option[Long])=>{
          val newBuff = buffer.getOrElse(0L) + seq.sum
          Option(newBuff)
        }
      )
      .print()

    ssc.start()

    ssc.awaitTermination()

  }
}
