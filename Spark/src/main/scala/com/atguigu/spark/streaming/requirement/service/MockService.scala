package com.atguigu.spark.streaming.requirement.service

import com.atguigu.spark.streaming.requirement.DAO.MockDAO
import com.atguigu.summer.core.Service

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-16 0:51
 */
class MockService extends Service{
    private val mockDAO = new MockDAO()
    
    override def analysis() = {
        
//        import mockDAO._
        
        val data = mockDAO.genMockData _//千万记住这里不能加括号
        
        mockDAO.writeToKafka(data)
    }
    
}
