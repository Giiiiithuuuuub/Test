package com.atguigu.scala.distribute3

import java.io.ObjectInputStream
import java.net.{ServerSocket, Socket}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-02 1:16
 */
object ResourceCenter {
  def main(args: Array[String]): Unit = {
    //准备获取Driver端的连接
    val serverSocket = new ServerSocket(9999)
    //可能有多个Driver，所有使用多线程处理
    while (true){
      val driverRef: Socket = serverSocket.accept()//获取Driver连接
      new Thread(
        new Runnable {
          override def run(): Unit = {
            //获取对象输入流
            val inputStream = new ObjectInputStream(driverRef.getInputStream)
            //读取对象并转化为Message
            val message: Message = inputStream.readObject().asInstanceOf[Message]
            //将数据按照&和=同时切分
            val strings: Array[String] = message.content.split("[&=]")
            //数据格式为：executorNum=$executorNum&driverHost=$driverHost&driverPort=$driverPort
            val executorNum : Int = strings(1).toInt
            val driverHost : String = strings(3)
            val driverPort : Int = strings(5).toInt
            //根据信息，创建多个Executor
            for(i <- 1 to executorNum){
              //创建
              val executor : Executor = Executor(i,driverHost,driverPort)
              //启动执行器
              executor.startup()
            }
          }
        }
      ).start()
    }
  }

}
