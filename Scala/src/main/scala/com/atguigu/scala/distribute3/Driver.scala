package com.atguigu.scala.distribute3

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.{ServerSocket, Socket}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-02 1:16
 */
object Driver {

  def main(args: Array[String]): Unit = {
    //数据准备
    var executorNum = 5
    val driverHost = "localhost"
    val driverPort = 1234

    //准备与ResourceCenter获取连接
    val socket = new Socket("localhost",9999)
    //将数据发送给ResourceCenter，用于启动Executor
    val outputStream = new ObjectOutputStream(socket.getOutputStream)
    outputStream.writeObject(new Message(s"executorNum=$executorNum&driverHost=$driverHost&driverPort=$driverPort"))
    outputStream.flush()
    socket.shutdownOutput()

    //准备和Executor建立连接
    val serverSocket : ServerSocket = new ServerSocket(driverPort)

    //声明一个数组，填充数字-1，用来判断是否所有的Executor都将数据发送完毕
    val array : Array[Int] = Array.fill(executorNum)(-1)//数组长度就是计算的数据量

    //建立一个线程，用来将计算结果汇总
    new Thread(
      new Runnable {
        override def run(): Unit = {
          //建立标记类，用来跳出循环
          var flag = true
          //持续判断是否计算完毕
          while (flag){
            if (array.contains(-1)){
              //如果包含-1，表示没有接收完毕，那么继续等待
              Thread.sleep(100)
            }else{
              //如果接收完毕，那么进行数据汇总并打印
              println(s"输出结果为：${array.sum}")
              //次处老师将flag置位false，但是实际上程序都被下面直接结束了
              //将整个程序停止
              System.exit(0)
            }
          }
        }
      }
    ).start()

    //由于多个Executor，所以采用多线程的方式
    while (true){
      val executorRef: Socket = serverSocket.accept()
      new Thread(
        new Runnable {
          override def run(): Unit = {
            //准备向Executor发送数据
            val objectOutputStream = new ObjectOutputStream(executorRef.getOutputStream)
            //创建任务类，将逻辑传出
            val task : Task = new Task
            task.logic = _*2
            objectOutputStream.writeObject(task)
            objectOutputStream.flush()
            executorRef.shutdownOutput()

            //获取每个Executor的计算结果

            val inputStream = new ObjectInputStream(executorRef.getInputStream)
            val message: Message = inputStream.readObject().asInstanceOf[Message]
            //同样，将读取的数据进行切分
            val strings: Array[String] = message.content.split("[&=]")
            val executroId : Int = strings(1).toInt
            val result : Int = strings(3).toInt
            //给数组每个元素赋值，executorID -1 刚好是索引的位置
            array(executroId -1) = result

          }
        }
      ).start()
    }
  }
}
