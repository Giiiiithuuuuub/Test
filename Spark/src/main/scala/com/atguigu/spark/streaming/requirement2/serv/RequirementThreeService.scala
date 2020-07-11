package com.atguigu.spark.streaming.requirement2.serv

import com.atguigu.spark.streaming.requirement2.clickLog
import com.atguigu.spark.streaming.requirement2.dao.RequirementThreeDAO
import core.SummerService
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.dstream.DStream

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-18 1:33
 */
class RequirementThreeService extends SummerService{
    private val reqThreeDAO = new RequirementThreeDAO
    
    override def analysis() = {
        val initDS: DStream[String] = reqThreeDAO.getFromKafka("requirement.properties")
    
        val changeDS: DStream[clickLog] = initDS.map(
            x => {
                val datas: Array[String] = x.split(" ")
                clickLog(datas(0), datas(1), datas(2), datas(3), datas(4))
            }
        )
        
        //转换格式：((广告，时间),1)
        changeDS
            .map(
                x => {
                    ((x.adid,x.ts.toLong/10000 * 10000),1)
                }
            )
            //开窗聚合
            .reduceByKeyAndWindow((x:Int,y:Int) => x + y,Seconds(60),Seconds(9))
            //转换格式
            .map{
                case((adid,day),sum)=>{
                    (adid,(day,sum))
                }
            }
            //按照广告分组
            .groupByKey()
            //按照时间排序
            .mapValues(
                x=>{
                    x.toList.sortWith(
                        (a,b)=>{
                            a._1 < b._1
                        }
                    )
                }
            )
            //打印
            .print()
    }
}
