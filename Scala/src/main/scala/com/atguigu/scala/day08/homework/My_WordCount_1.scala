package com.atguigu.scala.day08.homework

/**
 * @author GTZ
 * @create 2020-05-28 10:31
 */
object My_WordCount_1 {
  def main(args: Array[String]): Unit = {
    val list = List(("hello", 4),
      ("hello spark", 3),
      ("hello spark scala", 2),
      ("hello spark scala hive", 1))
    println(list)
    //将map拆分成一个list
    val wordlist: List[String] = list.map(
      mapp => {
        ((mapp._1+" ") * mapp._2).trim//去除尾部空格
      }
    )
    println(wordlist)
    //拆分每个list
    val stringses: List[Array[String]] = wordlist.map(
      str => {
        str.split(" ")
      }
    )
    val flatten: List[String] = stringses.flatten
    println(flatten)
    //分组
    val wordMap: Map[String, List[String]] = flatten.groupBy(
      word => word
    )
    println(wordMap)
    //计算每个单词个数
    val wordMapCount: Map[String, Int] = wordMap.map(
      wM => {
        (wM._1, wM._2.length)
      }
    )
    println(wordMapCount)
    //排序（降序）
    val wordMapCountSort: List[(String, Int)] = wordMapCount.toList.sortBy(
      wMC => {
        wMC._2
      }
    )(Ordering.Int.reverse)
    println(wordMapCountSort)
    //提取前三个
    println(wordMapCountSort.take(3))
  }

}
