package com.atguigu.spark.streaming.day03

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-14 10:09
 */
object QueueRDD {
    def main(args: Array[String]): Unit = {
        val ssc: StreamingContext = new StreamingContext(new SparkConf().setMaster("local[*]").setAppName("queue"), Seconds(3))
    
        val queue = new mutable.Queue[RDD[String]]()
    
        val inputStream: InputDStream[String] = ssc.queueStream(queue)
        
        inputStream
            .flatMap(_.split(" "))
            .map((_,1))
            .reduceByKey(_+_)
            .print()
        
        ssc.start()
        
        for(i <- 0 to 10){
            val temp: RDD[String] = ssc.sparkContext.makeRDD(List("a c", "b d", "c f", "aa aa aa"))
            
//            queue.enqueue(temp)
            
            Thread.sleep(1000)
        }
        
        ssc.awaitTermination()
        
    }
}
