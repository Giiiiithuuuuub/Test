package com.atguigu.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-26 0:52
 */
public class Test1 {

    @Test
    public void test() {
        List<String> list = Arrays.asList("Hello", "world", "atguigu", "Lambda", "ok");
        List<String> list1 = filterString(list, x -> x.length() > 3);

        list1.forEach(System.out::println);

    }

    public List<String> filterString(List<String> list, Predicate<String> predicate) {
        List<String> list1 = new ArrayList<>();
        for (String s : list) {
            if (predicate.test(s)) {
                list1.add(s);
            }
        }

        return list1;
    }

    @Test
    public void test2() {
        String newStr = strHandler("\t\t\t 我是钢铁侠   ", String::trim);
        System.out.println(newStr);
    }

    public String strHandler(String str, Function<String, String> fun) {
        return fun.apply(str);
    }

    @Test
    public void test3() {
        List<Integer> num = getNum(10, () -> (int) (Math.random() * 100));

        num.forEach(System.out::println);

    }

    public List<Integer> getNum(int num, Supplier<Integer> supp){
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            Integer integer = supp.get();

            list.add(integer);
        }
        return list;
    }

    @Test
    public void test4(){
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
    }

    @Test
    public void test5(){
        Function<Integer,String[]> fun = new Function<Integer, String[]>() {
            @Override
            public String[] apply(Integer integer) {
                return new String[integer];
            }
        };
    }
}
