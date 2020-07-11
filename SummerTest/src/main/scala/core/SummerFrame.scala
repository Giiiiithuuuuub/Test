package core

import org.apache.spark.streaming.{Duration, Seconds, StreamingContext}
import utils.EnvUtils

/**
 * 封装一个执行程序的 “框架”
 */
trait SummerFrame {
    var environmentVar : Any= _
    
    def startApp(`type` : String)(op: => Unit)(implicit time : Duration = Seconds(3)): Unit ={
        //如果是sparkStreaming程序，则启动该环境
        if (`type`.equalsIgnoreCase("sparkStreaming")){
            val ssc: StreamingContext = EnvUtils.getStreamingEnv()
            environmentVar = ssc
        }
        
        //执行控制抽象的逻辑
        try{
            op
        }catch {
            case ex : Exception => print("任务执行失败，Message：\n"  + ex.getMessage)
        }
        //action
        if(`type`.equalsIgnoreCase("sparkStreaming")){
            val ssc: StreamingContext = environmentVar.asInstanceOf[StreamingContext]
            ssc.start()
            ssc.awaitTermination()
        }
        
    }
}
