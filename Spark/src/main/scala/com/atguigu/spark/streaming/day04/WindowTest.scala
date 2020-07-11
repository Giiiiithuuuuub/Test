package com.atguigu.spark.streaming.day04

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-15 20:32
 */
object WindowTest {
    def main(args: Array[String]): Unit = {
        val ssc = new StreamingContext(new SparkConf().setMaster("local[*]").setAppName("join"), Seconds(3))
    
        ssc.checkpoint("output")
        
        val input: ReceiverInputDStream[String] = ssc.socketTextStream("localhost", 9999)
        
        
        input.flatMap(_.split(" "))
            .map((_,1))
//            .reduceByWindow((x,y)=>(x._1,y._2 + x._2),Seconds(9),Seconds(6))
            .countByWindow(Seconds(9),Seconds(6))
//            reduceByKey(_+_)
//            .reduceByKeyAndWindow(_+_,Seconds(6),Seconds(3))
//            .countByValueAndWindow(Seconds(6),Seconds(3))
            .print()
        
        ssc.start()
        
        ssc.awaitTermination()
    }
}
