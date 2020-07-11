package consumer.interceptor;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-08 23:08
 */
public class ProducerTest {
    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        List<String> interceptors = new ArrayList<>();
        interceptors.add("consumer.interceptor.InterceptorOne");
        interceptors.add("consumer.interceptor.InterCeptorTwo");
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,interceptors);
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost103:9092");
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        for (int i = 0; i < 10; i++) {

            producer.send(new ProducerRecord<>("first","atguigu--" + i));

        }

        producer.close();


    }
}
