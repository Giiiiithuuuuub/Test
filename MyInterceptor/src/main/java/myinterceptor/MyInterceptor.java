package myinterceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-05 15:15
 */
public class MyInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        Map<String, String> headers = event.getHeaders();
        String str = new String(event.getBody());

        if (str.contains("hello")){
            headers.put("key","word");
        }else {
            headers.put("key","num");
        }

        return event;
    }

    @Override
    public List<Event> intercept(List<Event> list) {
        for (Event event : list) {
            intercept(event);
        }

        return list;
    }

    @Override
    public void close() {

    }

    //
    public static class Builder implements Interceptor.Builder{

        //通过静态内部类创建类的对象
        @Override
        public Interceptor build() {
            return new MyInterceptor();
        }

        //有配置项的话可以写
        @Override
        public void configure(Context context) {

        }
    }
}
