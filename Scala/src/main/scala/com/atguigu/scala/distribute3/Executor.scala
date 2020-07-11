package com.atguigu.scala.distribute3

import java.io.{ObjectInputStream, ObjectOutput, ObjectOutputStream}
import java.net.Socket

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-02 1:16
 */
case class Executor(id : Int , driverHost : String, driverPort : Int){
  //没有返回值，所有等号也是可以省略
  def startup() {
    //执行器启动后，主动连接Driver
    val socket = new Socket(driverHost, driverPort)
    //获取Driver发来的数据
    val intputStream = new ObjectInputStream(socket.getInputStream)
    val task: Task = intputStream.readObject().asInstanceOf[Task]
    //关闭输入流
    socket.shutdownInput()
    //处理数据
    val result: Int = task.logic(id)
    //将处理完的数据发出
    val outputStream = new ObjectOutputStream(socket.getOutputStream)
    outputStream.writeObject(new Message(s"executorId=$id&result=$result"))
    //刷写并关闭资源
    outputStream.flush()
    socket.close()
  }
}
