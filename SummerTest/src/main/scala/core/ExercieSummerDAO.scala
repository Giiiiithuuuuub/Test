package core

import java.util.Properties

import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import utils.{EnvUtils, PropertiesUtils}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-18 10:24
 */
trait ExercieSummerDAO {
    
    
    
    
    
    def getDataFromKafka(propertiesName : String) = {
        //用kafka工具类创建DS，提供所有需要的参数即可，不需要记忆
    
        val properties: Properties = PropertiesUtils.getProperties(propertiesName)
        val topic: String = properties.getProperty("kafka-topic")
        
        val kafkaParams : Map[String,Object] = Map[String,Object](
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> properties.getProperty("kafka-brokerList"),
            ConsumerConfig.GROUP_ID_CONFIG -> properties.getProperty("kafka-groupId"),
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> "org.apache.kafka.common.serialization.StringDeserializer",
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> "org.apache.kafka.common.serialization.StringDeserializer"
            
        )
    
        val kafkaDS: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
            EnvUtils.getStreamingEnv,
            LocationStrategies.PreferConsistent,
            ConsumerStrategies.Subscribe[String, String](Seq(topic), kafkaParams)
        )
        
        //最后一步，就是获取的是value，与key无关
        
        kafkaDS.map(x => x.value())
    }
    
    def writeDataToKafka(propertiesName:String)(data:()=>Seq[String])={
    
        //创建kafka生产者，提供配置项
        val properties: Properties = PropertiesUtils.getProperties(propertiesName)
        val proper = new Properties()
        proper.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,properties.getProperty("kafka-brokerList"))
        proper.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer")
        proper.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer")
        
        val producer = new KafkaProducer[String, String](proper)
        
        //通过ProducerRecorder包装数据
        while (true){
            for (line <- data()){
                producer.send(new ProducerRecord[String,String](properties.getProperty("kafka-topic"),line))
                println(line)
            }
            
            Thread.sleep(2000)
            
        }
        
        
    }
    
}
