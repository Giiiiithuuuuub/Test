package producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-08 15:18
 */
public class MyProducer {
    public static void main(String[] args) throws InterruptedException {

        Properties properties = new Properties();
//        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,"partitioner.MyPartitioner");
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost103:9092");
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
//        properties.put(ProducerConfig.RETRIES_CONFIG,"3");
//        properties.put(ProducerConfig.LINGER_MS_CONFIG,"1");
//        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,"33554432");
//        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,"16384");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        for (int i = 0; i < 10000; i++) {
            Thread.sleep(500);
            producer.send(new ProducerRecord<>("second","这是第" + i + "条消息"), (x,y) -> {
                if (y == null){
                    System.out.println(x.partition() + "--" + x.offset());
                }
            });
        }

        producer.close();
    }
}
