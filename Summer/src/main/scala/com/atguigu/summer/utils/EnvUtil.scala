package com.atguigu.summer.utils

import org.apache.spark
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext, StreamingContextState}
import org.apache.spark.{SparkConf, SparkContext, streaming}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-09 10:11
 */
object EnvUtil {
    //Thread工具类的对象
    private val scLocal = new ThreadLocal[SparkContext]
    private val sscLocal = new ThreadLocal[StreamingContext]
    
    def getEnv(): SparkContext = {
        //从当前线程的共享内存空间中获取环境对象
        var sc: SparkContext = scLocal.get()
        
        if (sc == null) {
            //如果获取不到环境对象，创建新的环境对象，保存到共享内存中
            val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("sparkApplication")
            //创建新的环境对象
            sc = new SparkContext(sparkConf)
            //放到共享内存中
            scLocal.set(sc)
        }
        sc
    }
    
    def getStreamingEnv(implicit time: Int = 3): StreamingContext = {
        var ssc: StreamingContext = sscLocal.get()
        if (ssc == null) {
            val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("streamingApplication")
            
            ssc = new StreamingContext(sparkConf, Seconds(time))
            
            sscLocal.set(ssc)
        }
        
        ssc
    }
    
    def clear(): Unit = {
        getEnv().stop()
        //从共享内存中清除数据
        scLocal.remove()
    }
    
    def stopGracefully(): Unit = {
        
        new Thread(
            new Runnable {
                override def run(): Unit = {
                    val state: StreamingContextState = getStreamingEnv().getState()
                    
                    if (state == StreamingContextState.ACTIVE) {
                        getStreamingEnv.stop(true, true)
                        
                        System.exit(0)
                    }
                }
            }
        ).start()
        
    }
    
}


