package com.atguigu.summer.bean

 //定义一个任务类，用户创建对象设计逻辑，实现计算功能。
 //序列化从而可以使得对象在网络中传输
 class MyTask extends Serializable {
  var data: Int = 0
  var logic: Int => Int = null

  def compute(): Int ={
    logic(data)
  }
}
