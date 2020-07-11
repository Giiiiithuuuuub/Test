package com.atguigu.summer.utils

import java.io.InputStreamReader
import java.util.{Properties, ResourceBundle}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-16 0:07
 */
object PropertiesUtil {
    val summer: ResourceBundle = ResourceBundle.getBundle("summer")
    
    def getValue( key : String ): String = {
        val str: String = summer.getString(key)
        str
    }
}
