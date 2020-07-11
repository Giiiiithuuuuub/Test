package com.atguigu.scala.day09.homework

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-05-29 22:38
 */
object MerchandiseTest {
  def main(args: Array[String]): Unit = {
    val list = List(
      ("zhangsan", "河北", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "鞋"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河南", "衣服"),
      ("wangwu", "河南", "鞋"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "鞋"),
      ("zhangsan", "河北", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "帽子"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河南", "衣服"),
      ("wangwu", "河南", "帽子"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "帽子"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "电脑"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河南", "衣服"),
      ("wangwu", "河南", "电脑"),
      ("zhangsan", "河南", "电脑"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "帽子")
    )

    list.map(x => x._2 + "-" + x._3)
      .groupBy(x => x)
      .mapValues(_.size)
      .toList
      .map(x => (x._1.split("-")(0),(x._1.split("-")(1),x._2)))
      .groupBy(_._1)
      .mapValues(x =>{
        x.map(_._2)
          .sortWith(
            (x,y) =>{
              x._2 > y._2
            }
          )

      })foreach(println)



  }
}
