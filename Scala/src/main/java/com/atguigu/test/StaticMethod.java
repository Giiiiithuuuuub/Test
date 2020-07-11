package com.atguigu.test;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-31 1:20
 */
public class StaticMethod {

    public static int age = 20;

    static {
        System.out.println("会不会被执行");
    }
}
