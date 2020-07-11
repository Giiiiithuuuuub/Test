package com.atguigu.scala.day02

import java.io.{BufferedReader, InputStreamReader, OutputStreamWriter, PrintWriter}
import java.net.{ServerSocket, Socket}


/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-19 20:15
 */
object Server {
  def main(args : Array[String]) : Unit = {
    val serverSocket = new ServerSocket(9999)

    while(true){
      val socket = serverSocket accept
      val reader = new BufferedReader(new InputStreamReader(socket getInputStream))

      var s = ""
      var flag = true

      while (flag){
        s = reader readLine

        if (s != null){
          print(s)
        }else{
          flag = false
        }
      }
    }
  }
}

object Client{
 def main(args : Array[String]) : Unit = {
   val socket = new Socket("localhost",9999)

   val writer = new PrintWriter(new OutputStreamWriter(socket getOutputStream))

   writer print "I am IronMan"

   writer flush

   writer close

   socket close
 }
}
