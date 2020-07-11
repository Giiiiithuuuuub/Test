package com.atguigu.test.flume.interceptor;

import com.atguigu.test.flume.utils.ETLUtils;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.List;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-13 2:14
 */
public class ETLInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        byte[] body = event.getBody();
        String str = new String(body);

        if (ETLUtils.isValued(str)){
            return event;
        }
        return null;

    }

    @Override
    public List<Event> intercept(List<Event> list) {

        list.removeIf(x -> intercept(x) == null);
        return list;
    }

    public static class Builder implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            return new ETLInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }

    @Override
    public void close() {

    }
}
