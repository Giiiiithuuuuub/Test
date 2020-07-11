package consumer;

import org.apache.kafka.clients.consumer.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-08 20:22
 */
public class MyConsumer {
    public static void main(String[] args) throws InterruptedException {

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost103:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"IronMan2");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,true);
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");
//        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Arrays.asList("second","four"));

        while (true){
            ConsumerRecords<String, String> poll = consumer.poll(Duration.ofMillis(500)) ;
            for (ConsumerRecord<String, String> record : poll) {

                System.out.println(record.offset() + "--" + record.key() + "--" + record.value());
            }

        }
//        Thread.sleep(5000);
//        consumer.close();

    }
}
