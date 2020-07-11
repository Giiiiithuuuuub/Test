package utils

import java.sql.Connection
import java.util.Properties

import com.alibaba.druid.pool.DruidDataSourceFactory
import javax.sql.DataSource


/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-17 22:46
 */
object MyJDBCUtil {
    private var dataSource : DataSource = _

    private val pro: Properties = PropertiesUtils.getProperties("requirement.properties")

    def getConnection: Connection = {

        val properties = new Properties

        properties.put("username",pro.getProperty("jdbc.username"))
        properties.put("password",pro.getProperty("jdbc.password"))
        properties.put("url",pro.getProperty("jdbc.url"))
        properties.put("driverClassName",pro.getProperty("jdbc.driver"))
        properties.put("maxActive",pro.getProperty("jdbc.maxActive"))

        dataSource = DruidDataSourceFactory.createDataSource(properties)

        dataSource.getConnection

    }
    
//    var dataSource: DataSource = init()
//
//    def init(): DataSource = {
//        val paramMap = new java.util.HashMap[String,String]()
//        paramMap.put("driverClassName",pro.getProperty("jdbc.driver"))
//        paramMap.put("url",pro.getProperty("jdbc.url"))
//        paramMap.put("username",pro.getProperty("jdbc.username"))
//        paramMap.put("password",pro.getProperty("jdbc.password"))
//        paramMap.put("maxActive",pro.getProperty("jdbc.maxActive"))
//
//
//        val source: DataSource = DruidDataSourceFactory.createDataSource(paramMap)
//        source
//    }
//
//    def getConnection: Connection = {
//        dataSource.getConnection()
//    }
}
