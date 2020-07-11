package com.atguigu.spark.streaming.requirement.DAO

import java.util.Random

import com.atguigu.spark.streaming.requirement.{CityInfo, RanOpt}
import com.atguigu.summer.core.DAO
import org.apache.spark.rdd.RDD

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-16 0:53
 */
class MockDAO extends DAO{

    
    object RandomOptions {
        
        def apply[T](opts: RanOpt[T]*): RandomOptions[T] = {
            val randomOptions = new RandomOptions[T]()
            for (opt <- opts) {
                randomOptions.totalWeight += opt.weight
                for (i <- 1 to opt.weight) {
                    randomOptions.optsBuffer += opt.value
                }
            }
            randomOptions
        }
    }
    
    class RandomOptions[T](opts: RanOpt[T]*) {
        
        var totalWeight = 0
        var optsBuffer = new ListBuffer[T]
        
        def getRandomOpt: T = {
            val randomNum: Int = new Random().nextInt(totalWeight)
            optsBuffer(randomNum)
        }
    }

    implicit def genMockData() : Seq[String] = {
        val array: ArrayBuffer[String] = ArrayBuffer[String]()
        val CityRandomOpt = RandomOptions(RanOpt(CityInfo(1, "北京", "华北"), 30),
            RanOpt(CityInfo(2, "上海", "华东"), 30),
            RanOpt(CityInfo(3, "广州", "华南"), 10),
            RanOpt(CityInfo(4, "深圳", "华南"), 20),
            RanOpt(CityInfo(5, "天津", "华北"), 10))
        
        val random = new Random()
        // 模拟实时数据：
        for (i <- 0 to 50) {
            
            val timestamp: Long = System.currentTimeMillis()
            val cityInfo: CityInfo = CityRandomOpt.getRandomOpt
            val city: String = cityInfo.city_name
            val area: String = cityInfo.area
            val adid: Int = 1 + random.nextInt(6)
            val userid: Int = 1 + random.nextInt(6)
            
            array.append(timestamp + " " + area + " " + city + " " + userid + " " + adid)
        }
        array.toSeq
    }
    
    override def readFile(path: String): RDD[_] = ???
}
