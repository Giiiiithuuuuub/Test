package com.atguigu.java8;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.time.Instant;
import java.util.stream.Stream;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-27 0:21
 */
public class Test2 {

    @Test
    public void testStream(){
        Stream.iterate(2, x -> x + 2)
                .limit(10)
                .forEach(System.out::println);
    }

    @Test
    public void test3(){
        Stream.of(1,2,3,4,5,5)
                .forEach(System.out::println);
    }

    @Test
    public void test4(){
        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);
    }

    @Test
    public void test5(){
//        Instant.
    }
}
