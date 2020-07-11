package com.atguigu.spark.streaming.day03

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-14 10:09
 */
object TextFileStream {
    def main(args: Array[String]): Unit = {
        val ssc: StreamingContext = new StreamingContext(new SparkConf().setMaster("local[*]").setAppName("queue"), Seconds(3))
    
        val value: DStream[String] = ssc.textFileStream("input")
        
        value.print()
        
        ssc.start()
        
        ssc.awaitTermination()
        
    }
}
