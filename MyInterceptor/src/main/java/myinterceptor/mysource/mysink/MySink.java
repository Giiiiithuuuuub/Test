package myinterceptor.mysource.mysink;

import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-05 21:55
 */
public class MySink extends AbstractSink implements Configurable {
    Logger logger = LoggerFactory.getLogger(MySink.class);

    //1. define conf
    private String prefix;
    private String suffix;

    @Override
    public void configure(Context context) {
        //1. set conf
        prefix = context.getString("prefix");
        suffix = context.getString("suffix","atguigu");
    }

    @Override
    public Status process() throws EventDeliveryException {
        Status status = null;

        //1. get channel
        Channel channel = getChannel();
        //2. get transaction
        Transaction transaction = channel.getTransaction();
        //3. start
        transaction.begin();

        //4. do something
        try {
            Event event = channel.take();

            if (event != null){

                String str = new String(event.getBody());
                //5. get logger
                logger.info(prefix + "--" + str + "--" + suffix);
            }

            //6. commit
            transaction.commit();

            status = Status.READY;
        } catch (ChannelException e) {

            transaction.rollback();

            status = Status.BACKOFF;
        } finally {

            transaction.close();
        }

        return status;
    }
}
