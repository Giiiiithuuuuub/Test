package com.atguigu.summer.core
import java.util.Properties

import com.atguigu.summer.utils.{EnvUtil, ProUtils, PropertiesUtil}
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}

/**
 * @Description
 * @author Yu HaiFeng
 * @create 2020-06-08 23:02
 */
trait DAO {
  def readFile(path: String): RDD[_]
  

  def readFromKafka()={
  
    val brokerList : String = PropertiesUtil.getValue("kafka-broker-list")
    val groupID : String = PropertiesUtil.getValue("kafka.group.id")
    val topic : String = PropertiesUtil.getValue("kafka.topic")
    
    //3.定义Kafka参数
    val kafkaPara: Map[String, Object] = Map[String, Object](
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> brokerList,
      ConsumerConfig.GROUP_ID_CONFIG -> groupID,
      "key.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
      "value.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer"
    )

    //4.读取Kafka数据创建DStream
    val kafkaDStream: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
      EnvUtil.getStreamingEnv,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String](Set(topic), kafkaPara))

    //5.将每条消息的KV取出
    kafkaDStream.map(x => x.value())
  }

  def writeToKafka(implicit data:()=> Seq[String]): Unit ={
    val brokerList : String = ProUtils.getProperties("summer")("kafka-broker-list")
    val topic : String = PropertiesUtil.getValue("kafka.topic")
    val prop = new Properties()
    // 创建配置对象
    // 添加配置
    prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList)
    prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")


  // 创建Kafka消费者
    val kafkaProducer: KafkaProducer[String, String] = new KafkaProducer[String, String](prop)

    while (true) {
      // 随机产生实时数据并通过Kafka生产者发送到Kafka集群中
      for (line <- data()) {
        kafkaProducer.send(new ProducerRecord[String, String](topic, line))
        println(line)

      }
      Thread.sleep(2000)
    }
  }
}
