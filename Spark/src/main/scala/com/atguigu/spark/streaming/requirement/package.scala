package com.atguigu.spark.streaming

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-16 19:51
 */
package object requirement {
    
    case class CityInfo(city_id: Long,
                        city_name: String,
                        area: String)
    
    case class RanOpt[T](value: T, weight: Int)
    
    case class clickLog(ts: String, area: String, city: String, userId: String, adId: String)
    
}
