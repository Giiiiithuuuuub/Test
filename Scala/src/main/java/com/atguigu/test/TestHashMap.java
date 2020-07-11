package com.atguigu.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-19 19:07
 */
public class TestHashMap {

    public static void main(String[] args) {
        Map<UserTest,UserTest> map = new HashMap<>();

        for (int i = 0; i < 20; i++) {
            map.put(new UserTest(),new UserTest());

            System.out.println("i =" + i);
        }
    }
}

class UserTest {
    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}

