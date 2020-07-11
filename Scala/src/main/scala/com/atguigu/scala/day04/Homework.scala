package com.atguigu.scala.day04

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.{ServerSocket, Socket}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-23 0:11
 */
object Client {

  def main(args: Array[String]): Unit = {
    //创建socket
    val socket = new Socket("localhost",9999)

    //获得输出流，将自定义类输出
    val stream = new ObjectOutputStream(socket getOutputStream)

    stream writeObject new HomewrokTest

    //获得输入流，接收数据
    val inputStream = socket getInputStream

    val result = inputStream read

    //打印数据
    println(result)

    //关闭资源
    socket close

  }

}

object Server{
  def main(args: Array[String]): Unit = {
    val serverSocket = new ServerSocket(9999)
    //获得socket实例
    val socket = serverSocket accept

    //获得输入流，接收自定义类
    val inputStream = new ObjectInputStream(socket getInputStream)

    val myClass = inputStream readObject

    //反序列化自定义类，传入参数
    val finallyClass = myClass.asInstanceOf[HomewrokTest]

    val i = finallyClass myFunction 2

    //获得输出流，将数据写出
    val outputStream = socket getOutputStream

    outputStream write i

    //关闭socket即可
    socket close


  }
}
