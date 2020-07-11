package com.atguigu.java8.Exer1;

import java.util.function.BiFunction;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-26 23:58
 */
public class TestLambda {

    public static void main(String[] args) {
        //常规匿名实现类
        String s1 = toUpper("abcd", new Exer2Interface() {
            @Override
            public String getValue(String str) {
                return str.toUpperCase();
            }
        });
        System.out.println(s1);

        //Lambda表达式
        String s = toUpper("abcd", String::toUpperCase);
        System.out.println(s);

        System.out.println(toUpper("abcdefghij", x -> x.substring(1,4)));

        getSum(1L,2L,Long::sum);
        getSum(1L,2L,(x,y)-> x* y);
    }

    public static String toUpper(String str,Exer2Interface exer){
        return exer.getValue(str);
    }

    public static Long getSum(Long l1, Long l2 , BiFunction<Long,Long,Long> fun){
        return fun.apply(l1, l2);
    }
}
