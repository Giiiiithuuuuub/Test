package com.atguigu.scala.distribute3

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-02 1:13
 */
case class Task(){
  var data : Int = 0
  var logic : Int => Int = _

  def compute(): Int ={
    logic(data)
  }
}
