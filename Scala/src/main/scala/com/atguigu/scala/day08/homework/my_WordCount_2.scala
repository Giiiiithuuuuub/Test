package com.atguigu.scala.day08.homework

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 * @author GTZ
 * @create 2020-05-28 11:11
 */
object my_WordCount_2 {
  def main(args: Array[String]): Unit = {
  //导入数据
    val list = List(("hello", 4),
      ("hello spark", 3),
      ("hello spark scala", 2),
      ("hello spark scala hive", 1))
    println(list)
    //将每个字符串按照个数拆分出相应数量的单词输入一个list中
    val buffer: ListBuffer[String] = ListBuffer()
    for (i <- 0 until list.length){
      for (j<- 0 until list(i)._2 ){
        if(list(i)._1.contains(" ")) {
          val strings: Array[String] = list(i)._1.split(" ")
          for (k<- 0 until strings.length){
            buffer.append(strings(k))
          }
        }else  buffer.append(list(i)._1)
      }
    }
    println(buffer)
    //放到map里并计算出出现次数
    val sortBuffer: ListBuffer[String] = buffer.sortBy(word=>word)
    println(sortBuffer)
    val map: mutable.Map[String,Int] = mutable.Map()
    for (i<- 0 until buffer.length){
      val str=sortBuffer(i)
      if (map.getOrElse(str,0)>0){
        map.put(str,map.getOrElse(str,0)+1)
      }else map.put(str,1)
    }
    println(map)
    //转成list并按次数降序排序
    val tuples: List[(String, Int)] = map.toList.sortBy(
      mapp => {
        mapp._2
      }
    )(Ordering.Int.reverse)
    println(tuples)
    println(tuples.take(3))
  }

}
