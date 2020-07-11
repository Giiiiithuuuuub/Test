package com.atguigu.spark.streaming.day03

import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.{InputDStream, ReceiverInputDStream}
import org.apache.spark.streaming.receiver.Receiver
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-14 10:09
 */
object MyDStream {
    def main(args: Array[String]): Unit = {
        val ssc: StreamingContext = new StreamingContext(new SparkConf().setMaster("local[*]").setAppName("queue"), Seconds(3))
    
        val myreceiver: ReceiverInputDStream[String] = ssc.receiverStream(new MyDStreamTest("localhost", 9999))
        
        myreceiver.print()
        
        ssc.start()
        
        ssc.awaitTermination()
        
    }
}

class MyDStreamTest(host : String, port : Int) extends Receiver[String](storageLevel = StorageLevel.MEMORY_ONLY){
    private  var socket : Socket = _

    def receiver(): Unit ={
        val reader = new BufferedReader(new InputStreamReader(socket.getInputStream,"UTF-8"))

        val s : String = null
        while(true){
            val str: String = reader.readLine()

            if (str != null){

                store(str)
            }
        }
    }
    
    override def onStart(): Unit = {
        socket = new Socket(host,port)
        
        new Thread("Test"){
            setDaemon(true)//守护线程
            override def run(): Unit ={
                receiver()
            }
        }.start()
        
    }
    
    override def onStop(): Unit = {
        socket.close()
        socket = null//手动置空
    
    }
}
