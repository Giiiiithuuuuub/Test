package com.atguigu.spark.streaming.requirement.DAO

import com.atguigu.summer.core.DAO
import org.apache.spark.rdd.RDD

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-16 22:26
 */
class BlackListDAO extends DAO{
    override def readFile(path: String): RDD[_] = ???
    
    
}
