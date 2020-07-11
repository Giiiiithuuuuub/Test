package com.atguigu.java8;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Set;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-28 21:24
 */
public class NewTimeTest {

    @Test
    public void test1(){
        LocalDate now = LocalDate.now();
        System.out.println(now);

        LocalDate of = LocalDate.of(2020, 06, 1);

        System.out.println(of);

        LocalDate localDate = now.plusDays(2);
        System.out.println(localDate);

    }

    @Test
    public void test2(){
        Instant now = Instant.now();
        System.out.println(now);

        OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        long l = now.toEpochMilli();
        System.out.println(l);

        long l1 = offsetDateTime.toEpochSecond();
        System.out.println(l1);

        Duration between = Duration.between(Instant.now(), Instant.now().atOffset(ZoneOffset.ofHours(8)));
        long l2 = between.toMillis();
        System.out.println(l2);


    }

    @Test
    public void test3(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate localDate = LocalDate.of(1994, 07, 07);

        System.out.println(localDate);
    }

    @Test
    public void test4(){
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);


    }

    @Test
    public void test7(){
        //第一种方式：指定一个时区构建一个时间。默认是我们当地时间，设置其他地区时区，则生成的是该地区的时间。
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("America/Panama"));
        System.out.println(ldt);//2020-04-26T15:53:33.638
        //第二种方式：
        LocalDateTime ldt2= LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime zdt = ldt2.atZone(ZoneId.of("Asia/Shanghai"));//获得带时区的时间，这里要注意，通过这个ZoneDateTime只是构建一个带时区的时间，并不是真正的该地区的时间，想要真正构建带时区的该地区的时间，则上面那个 LocalDateTime.now(ZoneId.of("Asia/Shanghai"))需要带时区。
        System.out.println(zdt);//2020-04-26T15:53:33.638+08:00[Asia/Shanghai]

//        ZonedDateTime zonedDateTime = ldt.atZone(ZoneId.of("America/Panama"));

//        System.out.println(zonedDateTime);

//        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
//        availableZoneIds.forEach(System.out::println);

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("America/Panama"));
        System.out.println(now);
    }
}













































