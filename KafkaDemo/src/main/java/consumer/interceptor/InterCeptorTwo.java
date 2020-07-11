package consumer.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-08 23:08
 */
public class InterCeptorTwo implements ProducerInterceptor<String,String> {
    private int success;
    private int error;
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (metadata != null){
            success++;
        }else {
            error++;
        }
    }

    @Override
    public void close() {
        System.out.println("success:" + success + "\n" + "error:" + error);
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
