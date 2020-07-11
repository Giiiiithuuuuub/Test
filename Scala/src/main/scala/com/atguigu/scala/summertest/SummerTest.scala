package com.atguigu.scala.summertest

import com.atguigu.summer.bean.MyTask
import com.atguigu.summer.core.Summer

//object Client {
//  def main(args: Array[String]): Unit = {
//
//    Summer.MySummer("client")("summer.properties"){
//
//      //这一段为用户逻辑，本可以将逻辑写在外面，然后传入task即可，这里为了尝试控制抽象函数的特性
//      val task = new MyTask
//      //客户端传数据
//      task.data = 10
//      Summer.task = task
//    }
//
//
//  }
//}
//
//object Server{
//  def main(args: Array[String]): Unit = {
//    //第三个控制抽象函数只要声明，就要赋值，即便不使用。这里没有用到，所以随便赋一个值
//    Summer.MySummer("server")("summer.properties"){
//      //服务端传逻辑，并在框架内进行计算
//      Summer.task.logic = _ * 2
//    }
//  }
//}
