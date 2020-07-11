package com.atguigu.spark.sql.day02

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-12 20:51
 */
object HiveTest {
  def main(args: Array[String]): Unit = {

    System.setProperty("HADOOP_USER_NAME", "atguigu")

    val sparkSession: SparkSession = SparkSession
      .builder()
      .enableHiveSupport()
      .config(new SparkConf().setMaster("local[*]").setAppName("Hive"))
      .getOrCreate()

    import sparkSession.implicits._

    sparkSession.sql("use atguigu200213")

    sparkSession.sql(
      """
        |CREATE TABLE `user_visit_action`(
        |  `date` string,
        |  `user_id` bigint,
        |  `session_id` string,
        |  `page_id` bigint,
        |  `action_time` string,
        |  `search_keyword` string,
        |  `click_category_id` bigint,
        |  `click_product_id` bigint,
        |  `order_category_ids` string,
        |  `order_product_ids` string,
        |  `pay_category_ids` string,
        |  `pay_product_ids` string,
        |  `city_id` bigint)
        |row format delimited fields terminated by '\t'
        |""".stripMargin)

        sparkSession.sql(
          """
            |load data local inpath 'input/user_visit_action.txt' into table atguigu200213.user_visit_action
            |""".stripMargin)

    sparkSession.sql(
      """
        |CREATE TABLE `product_info`(
        |  `product_id` bigint,
        |  `product_name` string,
        |  `extend_info` string)
        |row format delimited fields terminated by '\t'
        |""".stripMargin)

    sparkSession.sql(
      """
        |load data local inpath 'input/product_info.txt' into table atguigu200213.product_info
        |""".stripMargin)

    sparkSession.sql(
      """
        |CREATE TABLE `city_info`(
        |  `city_id` bigint,
        |  `city_name` string,
        |  `area` string)
        |row format delimited fields terminated by '\t'
        |""".stripMargin)

    sparkSession.sql(
      """
        |load data local inpath 'input/city_info.txt' into table atguigu200213.city_info
        |""".stripMargin)


sparkSession.sql(
  """
    |select * from city_info
    |""".stripMargin).show(10)


  }
}
