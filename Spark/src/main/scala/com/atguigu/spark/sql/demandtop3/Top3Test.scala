package com.atguigu.spark.sql.demandtop3

import org.apache.spark.SparkConf
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, IntegerType, MapType, StringType, StructField, StructType}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-13 22:36
 */
object Top3Test {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .enableHiveSupport()
      .config(
        new SparkConf()
          .setMaster("local[*]")
          .setAppName("Top3")
      )
      .getOrCreate()

    import spark.implicits._

    spark.sql("use atguigu200213")

    spark.sql(
      """
        |select u.click_product_id,c.area,p.product_name,c.city_name
        |from user_visit_action u
        |join product_info p
        |on p.product_id = u.click_product_id
        |join city_info c
        |on u.city_id = c.city_id
        |where u.click_product_id>-1
        |""".stripMargin)
      .createOrReplaceTempView("t1")

    //使用自定义函数
    //由于city_name在下一个环节就排除了，所以在这里使用函数
    val uDAF = new MyTop3UDAF

    spark.udf.register("MyFun",uDAF)

    spark.sql(
      """
        |select area,product_name,count(*) as click_count,
        |MyFun(city_name) as agenda
        |from t1
        |group by area,product_name
        |""".stripMargin)
      .createOrReplaceTempView("t2")

    spark.sql(
      """
        |select * ,
        |rank() over(partition by area order by click_count desc) as rank
        |from t2
        |""".stripMargin)
      .createOrReplaceTempView("t3")


    spark.sql(
      """
        |select area,product_name,click_count,agenda
        |from t3
        |where rank <=3
        |""".stripMargin)
      .show()

    spark.stop()
  }
}

//自定义聚合函数，用来显示城市备注
class MyTop3UDAF extends UserDefinedAggregateFunction{
  override def inputSchema: StructType = {
    //输入的内容就是城市名称，所以为String类型
    StructType(Array(StructField("Top3",StringType)))
  }

  override def bufferSchema: StructType = {
    //缓冲区类型：(总点击数，(某个地区，该地区点击数))
    StructType(Array(
      StructField("totalClick",IntegerType),
      StructField("areaPercent",MapType(StringType,IntegerType))
    ))
  }

  override def dataType: DataType = {
    //输出类型：含有城市地区百分比的字符串
    StringType
  }

  //确定性，一般设置为true，表示输入和输出相对应
  override def deterministic: Boolean = true

  //缓冲区初始化
  override def initialize(buffer: MutableAggregationBuffer): Unit = {
    buffer(0) = 0
    buffer(1) = Map[String,Int]()//空集合
  }

  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    //缓冲区数据如何与传入的数据进行计算

    buffer(0) = buffer.getInt(0) + 1
    buffer(1) = buffer.getMap[String,Int](1).updated(input.getString(0),buffer.getAs[Map[String,Int]](1).getOrElse(input.getString(0),0) + 1)

    //注意：获取Map还有这个方法：getAs[泛型](位置)...表示将某个位置的值转换为Map格式
    //buffer(1) = buffer.getAs[Map[String,Int]](1).getOrElse(input.getString(0),0) + 1
  }

  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
    //将多个缓冲区数据进行两两合并

    buffer1(0) = buffer1.getInt(0) + buffer2.getInt(0)
    buffer1(1) = buffer1.getAs[Map[String,Int]](1).foldLeft(buffer2.getAs[Map[String,Int]](1)){
      case(a,(b,c))=>{
        a.updated(b,a.getOrElse(b,0) + c)
      }
    }
  }

  //最终输出的逻辑
  override def evaluate(buffer: Row): Any = {
    val totalcnt: Int = buffer.getInt(0)
    val citymap : collection.Map[String, Int]= buffer.getMap[String, Int](1)

    val s = new StringBuilder
    if (citymap.size >= 2){
      val temp: List[(String, Int)] = citymap.toList.sortWith(
        (left, right) => left._2 > right._2
      ).take(2)

      var rest = 0
      temp.foreach{
        case ( city, cnt ) =>
          val r : Int =  cnt * 100 / totalcnt
          s.append(city + r + "%,")
          rest = rest + r
      }
      s.toString() + "其他：" + (100 - rest) + "%"

    }else{

      s.append(citymap.head._1 + citymap.head._2*100/totalcnt + "%").toString()

    }


  }
}
