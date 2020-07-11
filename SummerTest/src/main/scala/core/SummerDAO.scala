package core

import java.util.Properties

import org.apache.kafka.clients.consumer
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, ConsumerStrategy, KafkaUtils, LocationStrategies, LocationStrategy}
import utils.{EnvUtils, PropertiesUtils}

/**
 * DAO层，用来对接数据
 */
trait SummerDAO {
    
    //封装一个从kafka获取数据的方法
    def getFromKafka(proName : String): DStream[String] ={
        
        //创建环境
        val ssc: StreamingContext = EnvUtils.getStreamingEnv()
        
        //从配置文件中读取配置
        val properties: Properties = PropertiesUtils.getProperties(proName)
        val brokerList: String = properties.getProperty("kafka-brokerList")
        val topic : String = properties.getProperty("kafka-topic")
        val groupId : String = properties.getProperty("kafka-groupId")
        
        val kafkaParams : Map[String,Object] = Map[String,Object](
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> brokerList,
            ConsumerConfig.GROUP_ID_CONFIG -> groupId,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> "org.apache.kafka.common.serialization.StringDeserializer",
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> "org.apache.kafka.common.serialization.StringDeserializer"
        )
        
        //创建DS
    
        val kafkaDS: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
            ssc,
            LocationStrategies.PreferConsistent,
            ConsumerStrategies.Subscribe[String, String](Set(topic), kafkaParams)
        )
        
        //获取数据
        kafkaDS.map(x => x.value())
    }
    
    //封装一个向kafka写数据的方法
    
    def writeToKafka(proName : String)(implicit data : () => Seq[String]): Unit ={
        val pro: Properties = PropertiesUtils.getProperties(proName)
        val brokerList: String = pro.getProperty("kafka-brokerList")
        val topic : String = pro.getProperty("kafka-topic")
        //读取配置文件
        val properties = new Properties()
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,brokerList)
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer")
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer")
        
        //创建kafka生产者
    
        val producer = new KafkaProducer[String, String](properties)
        
        
        //发送数据
        
        while (true){
            for (line <- data()){
                producer.send(new ProducerRecord[String,String](topic,line))
                
                println(line)
            }
            
            Thread.sleep(2000)
        }
    }
}
