package com.atguigu.summer.utils

import java.util.ResourceBundle

import com.atguigu.summer.core.Summer

//自定义读取配置文件的工具类
object ProUtils {
  
  //从Summer中获取配置文件的名字，然后获取属性
  def getProperties(proName : String)(key : String): String ={
  
    val bundle: ResourceBundle = ResourceBundle.getBundle(proName)
    
    val str: String = bundle.getString(key)
    
    str
  }

}
