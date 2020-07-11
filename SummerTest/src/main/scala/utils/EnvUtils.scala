package utils

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Duration, Durations, Seconds, StreamingContext}

/**
 * 将环境变量放到线程的缓存中，需要时直接获取
 */
object EnvUtils {
    //创建ThreadLocal对象
    private val threadLocal = new ThreadLocal[StreamingContext]
    
    def getStreamingEnv(implicit time : Duration = Seconds(3)): StreamingContext ={
        
        //从缓冲区中获取环境对象
    
        var ssc: StreamingContext = threadLocal.get()
        
        //如果缓冲区中没有，那么创建，并放入缓冲区
        if (ssc == null){
            
            ssc  = new StreamingContext(new SparkConf().setMaster("local[*]").setAppName("sscEnv"),time)
            
            threadLocal.set(ssc)
        }
        
        ssc
    }

}
