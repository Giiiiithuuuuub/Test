package com.atguigu.scala.distribute2

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.Socket

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-02 0:16
 */
case class Executor(id : Int, driverHost : String, driverPort : Int){
  def startup(){

    val socket = new Socket(driverHost, driverPort)

    val inputStream = new ObjectInputStream(socket.getInputStream)

    val task: Task = inputStream.readObject().asInstanceOf[Task]

    socket.shutdownInput()

    val i: Int = task.logic(id)

    val outputStream = new ObjectOutputStream(socket.getOutputStream)

    outputStream.writeObject(new Message(s"executorId=$id&result=$i"))

    outputStream.flush()

    socket.close()



  }
}
