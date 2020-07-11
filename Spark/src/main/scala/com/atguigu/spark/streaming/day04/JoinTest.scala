package com.atguigu.spark.streaming.day04

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-15 19:21
 */
object JoinTest {
    def main(args: Array[String]): Unit = {
        val ssc = new StreamingContext(new SparkConf().setMaster("local[*]").setAppName("join"), Seconds(3))
    
        val input1: ReceiverInputDStream[String] = ssc.socketTextStream("localhost", 9999)
        val input2: ReceiverInputDStream[String] = ssc.socketTextStream("localhost",8888)
    
        val value1: DStream[(String, Int)] = input1.flatMap(_.split(" ")).map((_, 1))
        val value2: DStream[(String,String)] = input2.flatMap(_.split(" ")).map((_,"a"))
    
        val result: DStream[(String, (Int, String))] = value1.join(value2)
        
        result.print()
        
        ssc.start()
        
        ssc.awaitTermination()
    }
}
