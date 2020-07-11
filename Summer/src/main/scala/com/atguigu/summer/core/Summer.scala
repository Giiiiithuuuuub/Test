package com.atguigu.summer.core

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.{ServerSocket, Socket}
import java.util.ResourceBundle

import com.atguigu.summer.bean.MyTask
import com.atguigu.summer.utils.{EnvUtil, ProUtils}
import org.apache.spark.streaming.{StreamingContext, StreamingContextState}
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

//封装的summer框架
trait Summer {
    
    //将套接字提出来，方便使用
    var envData: Any = _
    var task: MyTask = _
    var pro: ResourceBundle = _
    //配置文件的名字
    
    //使用柯里化，用来传多个参数：业务类型，配置文件
    //使用控制抽象函数，用来输入用户自己的业务逻辑
    def MySummer(typ: String)(proper: String)(op: => Unit): Unit = {
        
        //绑定配置文件：这个方法专门用来读取配置文件，所以不加扩展名，就可以识别配置文件
        //而且肯定不能加，否则就会报错
        
        //通过获取传入的参数，来获取配置文件除了扩展名之外的名字
        
        //1.初始化环境
        if ("client".equalsIgnoreCase(typ)) {
            //获得Socket
            envData = new Socket(ProUtils.getProperties(proper)("server.host"), ProUtils.getProperties(proper)("server.port").toInt)
            //获得对象输出流
            val outputStream = getMyOutputStream(envData)
            //用户逻辑
            try {
                op
            } catch {
                case ex: Exception => println("业务执行失败：" + ex.getMessage)
            }
            //将用户提交对象输出
            outputStream.writeObject(task)
            outputStream.flush()
            val socket = envData.asInstanceOf[Socket]
            //关闭输出流，输入流还在
            socket.shutdownOutput()
            
            //获得客户端返回的数据
            val inputStream = getMyInputStream(envData)
            val result = inputStream.readObject().asInstanceOf[Int]
            println("输出结果为：" + result)
            //关闭输入流
            socket.shutdownInput()
            
        } else if ("server".equalsIgnoreCase(typ)) {
            //获得ServerSocket
            envData = new ServerSocket(ProUtils.getProperties(proper)("server.port").toInt)
            
            val serverSocket = envData.asInstanceOf[ServerSocket]
            //循环来处理多个客户端
            while (true) {
                var socket = serverSocket.accept()
                //通过多线程的方式，实现并发处理客户端的请求
                new Thread(
                    new Runnable {
                        override def run(): Unit = {
                            val inputStream = getMyInputStream(socket)
                            val tk = inputStream.readObject().asInstanceOf[MyTask]
                            //将传入的task再赋值给属性，从而使得传入逻辑可以使用该属性
                            task = tk
                            socket.shutdownInput()
                            //对客户端传进来的内容进行计算，并输出
                            try {
                                op
                            } catch {
                                case ex: Exception => println("任务执行失败" + ex.getMessage)
                            }
                            val result = task.compute()
                            val outputStream = getMyOutputStream(socket)
                            outputStream.writeObject(result)
                            outputStream.flush()
                            outputStream.close()
                            
                            if (!socket.isClosed) {
                                socket.close()
                            }
                            //这里显示的将socket置为零，为的是尽快垃圾回收
                            socket = null
                            
                        }
                    }
                ).start()
            }
            
        } else if ("spark".equalsIgnoreCase(typ)) {
            try {
                op
            } catch {
                case ex: Exception => println("任务执行失败" + ex.getMessage)
            }
        } else if ("sparkStreaming".equalsIgnoreCase(typ)) {
            envData = EnvUtil.getStreamingEnv
            
            try {
                op
            } catch {
                case ex: Exception => println("任务执行失败" + ex.getMessage)
            }
        }
        
        
        //3.关闭资源
        if ("server".equalsIgnoreCase(typ)) {
            val socket = envData.asInstanceOf[Socket]
            if (!socket.isClosed) {
                socket.close()
            }
        } else if ("client".equalsIgnoreCase(typ)) {
            val serverSocket = envData.asInstanceOf[Socket]
            if (!serverSocket.isClosed) {
                serverSocket.close()
            }
        } else if ("spark".equalsIgnoreCase(typ)) {
            EnvUtil.clear()
        } else if ("sparkStreaming".equalsIgnoreCase(typ)) {
            val ssc = envData.asInstanceOf[StreamingContext]
            
            
            ssc.start()
            
            ssc.awaitTermination()
        }
    }
    
    
    //通过反射获取第一个方法的第一个参数
    def getPropertiesName: String = {
        val methods = getClass.getDeclaredMethods
        val method = methods(0)
        val parameters = method.getParameters
        parameters(0).getName
    }
    
    //获得对象输入流
    def getMyInputStream(mySocket: Any): ObjectInputStream = {
        val socket = mySocket.asInstanceOf[Socket]
        val inputStream = new ObjectInputStream(socket.getInputStream)
        inputStream
    }
    
    //获得对象输出流
    def getMyOutputStream(mySocket: Any): ObjectOutputStream = {
        val socket = mySocket.asInstanceOf[Socket]
        val outputStream = new ObjectOutputStream(socket.getOutputStream)
        outputStream
    }
    
    
}
