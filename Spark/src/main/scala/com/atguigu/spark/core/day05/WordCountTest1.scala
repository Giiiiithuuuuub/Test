package com.atguigu.spark.core.day05

import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.util.AccumulatorV2
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-05 20:23
 */
object WordCountTest {
  def main(args: Array[String]): Unit = {
    val sparkContext: SparkContext = new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("WordCount")
    )

    val value: RDD[(String, Int)] = sparkContext.textFile("InputTest/Test.txt")
      .flatMap(_.split(" "))
      .map((_, 1))
      .reduceByKey(_ + _)

    value.persist(StorageLevel.DISK_ONLY)
    value.collect().foreach(println)
    value.saveAsObjectFile("output")
    sparkContext.objectFile("output")

    sparkContext.stop()
  }

}

object WordCountTest1 {
  def main(args: Array[String]): Unit = {
    val sparkContext: SparkContext = new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("WordCount1")
    )

    sparkContext.textFile("InputTest/Test.txt")
      .flatMap(_.split(" "))
      .groupBy(x => x)
      .map(x => (x._1, x._2.size))
      .collect()
      .foreach(println)

    sparkContext.stop()
  }
}

object WordCountTest2 {
  def main(args: Array[String]): Unit = {
    val sparkContext: SparkContext = new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("WordCount2")
    )

    sparkContext.textFile("InputTest/Test.txt")
      .flatMap(_.split(" "))
      .map((_, 1))
      .groupByKey()
      .map(x => (x._1, x._2.sum))
      .collect()
      .foreach(println)

    sparkContext.stop()
  }
}

object WordCountTest3 {
  def main(args: Array[String]): Unit = {
    val sparkContext: SparkContext = new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("WordCount3")
    )

    sparkContext.textFile("InputTest/Test.txt")
      .flatMap(_.split(" "))
      .map((_, 1))
      .aggregateByKey(0)(_ + _, _ + _)
      .collect()
      .foreach(println)

    sparkContext.stop()
  }
}

object WordCountTest4 {
  def main(args: Array[String]): Unit = {
    val sparkContext: SparkContext = new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("WordCount4")
    )

    sparkContext.textFile("InputTest/Test.txt")
      .flatMap(_.split(" "))
      .map((_, 1))
      .foldByKey(0)(_ + _)
      .collect()
      .foreach(println)

    sparkContext.stop()
  }
}

object WordCountTest5 {
  def main(args: Array[String]): Unit = {
    val sparkContext: SparkContext = new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("WordCount5")
    )

    sparkContext.textFile("InputTest/Test.txt")
      .flatMap(_.split(" "))
      .map((_, 1))
      .combineByKey(
        x => x,
        (x: Int, y) => x + y,
        (x: Int, y: Int) => x + y
      ).collect()
      .foreach(println)

    sparkContext.stop()
  }
}

object WordCountTest6 {
  def main(args: Array[String]): Unit = {
    val sparkContext: SparkContext = new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("WordCount6")
    )

    sparkContext.textFile("InputTest/Test.txt")
      .flatMap(_.split(" "))
      .map((_, 1))
      .countByKey()
      .foreach(println)

    sparkContext.stop()
  }
}

object WordCountTest7 {
  def main(args: Array[String]): Unit = {
    val sparkContext: SparkContext = new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("WordCount7")
    )

    sparkContext.textFile("InputTest/Test.txt")
      .flatMap(_.split(" "))
      .countByValue()
      .foreach(println)

    sparkContext.stop()
  }
}

object WordCountTest8 {
  def main(args: Array[String]): Unit = {
    val sparkContext: SparkContext = new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("WordCount8")
    )

    sparkContext.textFile("InputTest/Test.txt")
      .flatMap(_.split(" "))
      .map(x => Map[String, Int]((x, 1)))
      .reduce(
        (x, y) => x.foldLeft(y)(
          (map, kv) => {
            map.updated(kv._1, map.getOrElse(kv._1, 0) + kv._2)
          }
        )
      ).foreach(println)
    sparkContext.stop()
  }
}

object WordCountTest9 {
  def main(args: Array[String]): Unit = {
    val sparkContext: SparkContext = new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("WordCount9")
    )

    sparkContext.textFile("InputTest/Test.txt")
      .flatMap(_.split(" "))
      .map(x => Map[String, Int]((x, 1)))
      .fold(Map.empty)(
        (x, y) => x.foldLeft(y)(
          (map, kv) => {
            map.updated(kv._1, map.getOrElse(kv._1, 0) + kv._2)
          }
        )
      ).foreach(println)
    sparkContext.stop()
  }
}

object WordCountTest10 {
  def main(args: Array[String]): Unit = {
    val sparkContext: SparkContext = new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("WordCount10")
    )

    sparkContext.textFile("InputTest/Test.txt")
      .flatMap(_.split(" "))
      .aggregate(Map[String, Int]())(
        (x, y) => x.updated(y, x.getOrElse(y, 0) + 1),
        (x, y) => x.foldLeft(y)(
          (map, kv) => {
            map.updated(kv._1, map.getOrElse(kv._1, 0) + kv._2)
          }
        )
      ).foreach(println)
    sparkContext.stop()
  }
}

object WordCountTest11 {
  def main(args: Array[String]): Unit = {
    val sparkContext: SparkContext = new SparkContext(
      new SparkConf()
        .setMaster("local[*]")
        .setAppName("WordCount11")
    )
    //创建累加器
    val myAccumulator = new MyAccumulator
    //注册
    sparkContext.register(myAccumulator)
    sparkContext.textFile("InputTest/Test.txt")
      .flatMap(_.split(" "))
      .foreach(
        x => {
          //使用累加器
          myAccumulator.add(x)
        }
      )
    //获取结果
    myAccumulator.value.foreach(println)

    sparkContext.stop()
  }
}

class MyAccumulator extends AccumulatorV2[String, mutable.Map[String, Int]] {
  //定义一个集合，用来保存word-count
  var wordCount: mutable.Map[String, Int] = mutable.Map[String, Int]()

  //累加器是否初始化
  override def isZero: Boolean = {
    //这里就是判断，累加器是否没有做过累加计算
    //只要我们的wordCount集合没有值，说明没有累加过
    wordCount isEmpty
  }

  //复制累加器
  override def copy(): AccumulatorV2[String, mutable.Map[String, Int]] = {
    new MyAccumulator
  }

  //重置累加器：就是说不管这个累加器是什么，让它重置，然后重头开始计算
  override def reset(): Unit = {
    //把它清空，就相当于重置
    wordCount clear()
  }

  //向累加器中增加值
  override def add(word: String): Unit = {
    wordCount(word) = wordCount.getOrElse(word, 0) + 1
  }

  //合并：用来合并累加器
  override def merge(other: AccumulatorV2[String, mutable.Map[String, Int]]): Unit = {
    val map1: mutable.Map[String, Int] = wordCount
    val map2: mutable.Map[String, Int] = other.value

    //这里将累加器合并之后，更新wordCount集合，从而可以跟之后的累加器继续合并
    wordCount=  map1.foldLeft(map2) {
      (x, y) =>
        x.update(y._1, x.getOrElse(y._1, 0) + y._2)
        x
    }
  }

  //返回累加器的值
  override def value: mutable.Map[String, Int] = {
    wordCount
  }
}

