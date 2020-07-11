package com.atguigu.test;

public class TestOverride {

    public static void main(String[] args) {
        // 第四个问题：30
        Person2 user = new User2();
        System.out.println(user.sum());
    }

}
class Person2 {

    public int i = 10;
    public int sum() {
        return getI() + i;
    }
    public int getI() {
        return i;
    }

}
class User2 extends Person2 {
    public int i = 20;
    public int getI() {
        return i;
    }
}


