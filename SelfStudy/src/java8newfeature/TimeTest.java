package java8newfeature;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-26 15:43
 */
public class TimeTest {
    @Test
    public void test(){
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(ldt);
    }

    @Test
    public void test7(){
        //第一种方式：指定一个时区构建一个时间。默认是我们当地时间，设置其他地区时区，则生成的是该地区的时间。
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(ldt);
        //第二种方式：
        LocalDateTime ldt2= LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime zdt = ldt2.atZone(ZoneId.of("Asia/Shanghai"));//获得带时区的时间，这里要注意，通过这个ZoneDateTime只是构建一个带时区的时间，并不是真正的该地区的时间，想要真正构建带时区的该地区的时间，则上面那个 LocalDateTime.now(ZoneId.of("Asia/Shanghai"))需要带时区。
        System.out.println(zdt);
    }
}
