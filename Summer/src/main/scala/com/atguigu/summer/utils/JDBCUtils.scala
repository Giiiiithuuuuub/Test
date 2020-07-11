package com.atguigu.summer.utils

import java.sql.Connection
import java.util

import com.alibaba.druid.pool.DruidDataSourceFactory
import javax.sql.DataSource


/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-17 0:57
 */
object JDBCUtils {

    
    var dataSource: DataSource = init()
    
    def init(): DataSource = {
        val paramMap = new util.HashMap[String,String]()
        paramMap.put("driverClassName",PropertiesUtil.getValue("jdbc.driver.name"))
        paramMap.put("url",PropertiesUtil.getValue("jdbc.url"))
        paramMap.put("username",PropertiesUtil.getValue("jdbc.username"))
        paramMap.put("password",PropertiesUtil.getValue("jdbc.password"))
        paramMap.put("maxActive",PropertiesUtil.getValue("jdbc.datasource.size"))
    
    
        val source: DataSource = DruidDataSourceFactory.createDataSource(paramMap)
        source
    }
    
    def getConnection: Connection = {
        dataSource.getConnection()
    }
}
