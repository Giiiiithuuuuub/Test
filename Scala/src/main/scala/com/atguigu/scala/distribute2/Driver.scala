package com.atguigu.scala.distribute2

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.{ServerSocket, Socket}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-02 0:26
 */
object Driver {

  def main(args: Array[String]): Unit = {

    var executorNum = 5
    val myHost = "localhost"
    val myPort = 1234

    val driverSocket : Socket = new Socket("localhost",9999)
    val outputStream = new ObjectOutputStream(driverSocket.getOutputStream)
    outputStream.writeObject(new Message(s"executorNum=$executorNum&myHost=$myHost&myPort=$myPort"))
    outputStream.flush()
    driverSocket.shutdownOutput()

    val serverSocket = new ServerSocket(myPort)
    val temp : Array[Int] = Array.fill(executorNum)(-1)

    new Thread(
      new Runnable {
        override def run(): Unit = {
          var flag = true
          while (flag){
            if (temp.contains(-1)){
              Thread.sleep(100)
            }else{
              println(
                s"""
                  |输出结果为：${temp.sum}
                  |""".stripMargin)

              flag = false
              System.exit(0)
            }
          }
        }
      }
    ).start()

    while (true){
      val socket: Socket = serverSocket.accept()
      new Thread(
      new Runnable {
        override def run(): Unit = {
          val outputStream1 = new ObjectOutputStream(socket.getOutputStream)
          val task : Task = new Task
          task.logic = _*2
          outputStream1.writeObject(task)
          outputStream1.flush()
          socket.shutdownOutput()

          val inputStream = new ObjectInputStream(socket.getInputStream)

          val message: Message = inputStream.readObject().asInstanceOf[Message]
          val strings: Array[String] = message.content.split("[&=]")
          val id  : Int = strings(1).toInt
          val result : Int = strings(3).toInt

          temp(id - 1)=result

        }
      }
      ).start()


    }
  }
}
