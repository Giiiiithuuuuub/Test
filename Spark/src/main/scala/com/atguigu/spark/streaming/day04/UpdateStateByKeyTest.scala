package com.atguigu.spark.streaming.day04

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-15 19:39
 */
object UpdateStateByKeyTest {
    def main(args: Array[String]): Unit = {
        val ssc = new StreamingContext(new SparkConf().setMaster("local[*]").setAppName("updateStateByKey"), Seconds(3))
        ssc.checkpoint("output")
    
        val temp: ReceiverInputDStream[String] = ssc.socketTextStream("localhost", 9999)
        
        
        //将函数作为整体赋给变量
        val needFun: (Seq[Int], Option[Int]) => Option[Int] = (values : Seq[Int], state : Option[Int]) => {
            val currentCount: Int = values.sum
            val oldCount: Int = state.getOrElse(0)
            
            Some(currentCount + oldCount)
        }
        
        
        temp.flatMap(_.split(" "))
            .map((_,1))
            .updateStateByKey[Int](needFun)
            .print()
        
        ssc.start()
        
        
        ssc.awaitTermination()
        
        
    }
}
