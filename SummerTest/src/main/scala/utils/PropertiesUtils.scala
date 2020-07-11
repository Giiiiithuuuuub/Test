package utils

import java.io.InputStream
import java.util.Properties

/**
 * 封装读取配置文件的工具类
 */
object PropertiesUtils {
    
    def getProperties(proName : String): Properties ={
        //通过反射的方式，获取配置文件的输入流
        val inputStream: InputStream = Thread.currentThread().getContextClassLoader.getResourceAsStream(proName)
        //创建properties对象
        val properties : Properties = new Properties()
    
        //加载配置文件
        properties.load(inputStream)
    
        //将含有配置文件的properties返回
        properties
    
    }
}
