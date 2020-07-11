package com.atguigu.spark.core.demandfour

import com.atguigu.summer.core.Service
import org.apache.spark.rdd.RDD

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-09 18:49
 */
class PageService extends Service {
  private val pageDAO = new PageDAO

  override def analysis() = {
    //对指定页面流程进行单跳转换率的统计
    //设定需要的集合
    val needID = List(1, 2, 3, 4, 5, 6, 7)
    //格式转换 (1,2)(2,3)(3,4)....
    val formattedID: List[(Int,Int)] = needID.zip(needID.tail)
    //获取数据
    val initRDD: RDD[UserVisitAction] = pageDAO.readFile("InputTest/user_visit_action.txt")
    initRDD.cache()

    //计算分母  (页面id，访问数量)
    val denominator: Array[(Long, Int)] = initRDD
      .filter(
        x=>{
          //这里不需要统计7，因为6-7/6，跟7是多少没关系
          needID.init.contains(x.page_id.toInt)
        }
      )
      .map(
        x => {
          (x.page_id, 1)
        }
      )
      .reduceByKey(_ + _)
      .collect()

    // 计算分子

    val numerator: Array[(String, Int)] = initRDD
      .groupBy(_.session_id) //按照sessionId分组，从而可以保证同一用户时间的访问在同一组
      .mapValues(
        x => {
          val pageId: List[Long] = x.toList.sortWith( //同一组内，按照访问时间排序，保证先后顺序
            (l, r) => {
              l.action_time < r.action_time
            }
          )
            .map(_.page_id) //只需要保留页面ID这个有用信息即可
          val tempChange: List[(Long, Long)] = pageId.zip(pageId.tail) //实现(1,2)(2,3)(3,4)的类型转换
              .filter (
                x => {
                  formattedID.contains(x)
                }
          )//在组合之后过滤，减少无效数据。不能在组合之前过滤，因为数据要连续访问
          tempChange.map {
            case (a, b) => {
              (a + "-" + b, 1) //进行最终格式转换：(1-2,1)(2-3,1)(3-4,1)
            }
          }

        }

      )
      //不需要sessionID，只需要保留格式化后的数据
      .map(_._2)
      //这里是把每个人的页面跳转当做整体，所以要扁平化，为了和别人的页面跳转进行合并
      .flatMap(x => x)
      .reduceByKey(_ + _)
      .collect()


    numerator.foreach {
      case (a, b) => {
        val pageid: String = a.split("-")(0)
        val value: Int = denominator.toMap.getOrElse(pageid.toLong, 1)
        println("页面跳转" + a + "转换率" + (b.toDouble / value).formatted("%.3f"))
      }
    }

  }


}
