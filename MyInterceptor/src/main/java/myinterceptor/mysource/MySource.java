package myinterceptor.mysource;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.PollableSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.SimpleEvent;
import org.apache.flume.source.AbstractSource;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-05 18:25
 */
public class MySource extends AbstractSource implements Configurable, PollableSource {
    private String prefix;
    private String suffix;
    private Integer sleep;

    @Override
    public void configure(Context context) {
        //自定义配置项
        prefix = context.getString("prefix");
        suffix = context.getString("suffix","atguigu");
        sleep = context.getInteger("sleep",5000);
    }

    @Override
    public Status process() throws EventDeliveryException {
        Status status = null;
        //1. 模拟读取数据
        try {
            for (int i = 0; i < 5; i++) {
                //2. 获取事件的实现类，将数据封装到数据中
                SimpleEvent event = new SimpleEvent();

                event.setBody((prefix + "--" + i + "--" + suffix).getBytes());
                //3. 提交给channel处理器
                getChannelProcessor().processEvent(event);

                //4. 提交成功与否，返回状态
                status = Status.READY;
            }
        } catch (Exception e) {
            status = Status.BACKOFF;
        }

        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public long getBackOffSleepIncrement() {
        return 0;
    }

    @Override
    public long getMaxBackOffSleepInterval() {
        return 0;
    }
}
