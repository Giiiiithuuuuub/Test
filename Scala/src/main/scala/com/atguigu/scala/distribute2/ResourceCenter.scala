package com.atguigu.scala.distribute2

import java.io.ObjectInputStream
import java.net.{ServerSocket, Socket}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-02 0:26
 */
object ResourceCenter {
  def main(args: Array[String]): Unit = {
    val serverSocket: ServerSocket = new ServerSocket(9999)
    while (true) {
      val driverRef: Socket = serverSocket.accept()
      new Thread(
      new Runnable {
          override def run(): Unit = {
            val inputStream = new ObjectInputStream(driverRef.getInputStream)
            val message: Message = inputStream.readObject().asInstanceOf[Message]
            val strings: Array[String] = message.content.split("[&=]")
            //"executorNum=$executorNum&myHost=$myHost&myPort=$myPort"
            val executorNum: Int = strings(1).toInt
            val myHost: String = strings(3)
            val myPort: Int = strings(5).toInt

            for (i <- 1 to executorNum){
              val executor : Executor = Executor(i,myHost,myPort)
              executor.startup()
            }


          }
        }
      ).start()
    }
  }
}
