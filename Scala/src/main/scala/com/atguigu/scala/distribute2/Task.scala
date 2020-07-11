package com.atguigu.scala.distribute2

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-02 0:18
 */
case class Task(){

  var data : Int = 0
  var logic: Int => Int = null

  def compute(): Int ={
    logic(data)
  }
}
