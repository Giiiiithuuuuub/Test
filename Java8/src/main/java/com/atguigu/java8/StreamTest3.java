package com.atguigu.java8;

import com.atguigu.java8.Exer1.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-27 0:47
 */
public class StreamTest3 {
    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66),
            new Employee(101, "张三", 18, 9999.99),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    @Test
    public void test3(){
        long count = emps.stream()
                .count();
        System.out.println(count);

        Optional<Employee> max = emps.stream().max((x, y) -> Double.compare(x.getSalary(), y.getSalary()));
        System.out.println(max);


    }


    @Test
    public void test1() {
        emps.stream()
                .map((e) -> e.getName())
                .collect(Collectors.toCollection(HashSet::new));


        System.out.println("-------------------------------------------");

        List<String> strList = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");

        strList.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);


        Stream<Stream<Character>> stream2 = strList.stream()
                .map(StreamTest3::filterCharacter);

        stream2.forEach((sm) -> {
            sm.forEach(System.out::println);
        });

        System.out.println("---------------------------------------------");

        Stream<Character> stream3 = strList.stream()
                .flatMap(StreamTest3::filterCharacter);

        stream3.forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();

        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }

        return list.stream();
    }
}
