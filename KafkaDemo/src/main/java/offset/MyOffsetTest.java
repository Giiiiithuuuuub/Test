package offset;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.*;

public class MyOffsetTest {
    private static Map<TopicPartition, Long> currentOffset = new HashMap<>();

    public static void main(String[] args) {
        //创建配置信息
        Properties props = new Properties();
        //Kafka集群
        props.put("bootstrap.servers", "hadoop102:9092");
        //消费者组，只要group.id相同，就属于同一个消费者组
        props.put("group.id", "test");
        //关闭自动提交offset
        props.put("enable.auto.commit", "false");
        //Key和Value的反序列化类
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //创建一个消费者
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        //消费者订阅主题
        consumer.subscribe(Arrays.asList("first", "second"), new ConsumerRebalanceListener() {
            //Rebalance之前
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                commitOffset(currentOffset);
            }

            //Reblance之后
            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {

            }
        });
        //消费数据
        while (true){
            ConsumerRecords<String, String> poll = consumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<String, String> record : poll) {
                //模拟消费数据的场景，简单的打印
                System.out.println(record.offset() + "--" + record.partition() + "--" + record.key());
                //将消费过的数据的元数据信息，以及offset信息，封装到Map中
                currentOffset.put(new TopicPartition(record.topic(),record.partition()),record.offset());
            }

            commitOffset(currentOffset);

        }

    }

    //获取某分区的最新offset
    private static long getOffset(TopicPartition partition) {
        //内部编写从MySQL中获取分区offset的逻辑
        return 0;
    }

    //提交该消费者所有分区的offset
    private static void commitOffset(Map<TopicPartition, Long> currentOffset) {
        //内部编写提交到MySQL的JDBC逻辑
    }
}