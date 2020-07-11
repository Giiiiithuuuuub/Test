package com.atguigu.java8;

import com.atguigu.java8.Exer1.Employee;
import org.junit.Test;

import java.lang.reflect.Array;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-28 23:48
 */
public class StreamAPITest {

    List<Employee> emps = Arrays.asList(
            new Employee(102, "lisi", 59, 6666.66),
            new Employee(101, "zhangsan", 18, 9999.99),
            new Employee(103, "wangwu", 28, 3333.33),
            new Employee(104, "zhaoliu", 8, 7777.77),
            new Employee(104, "zhaoliu", 8, 7777.77),
            new Employee(104, "zhaoliu", 8, 7777.77),
            new Employee(105, "tianqi", 38, 5555.55),
            new Employee(106, "wangba", 28, 5535.09)
    );

    @Test
    public void test14(){
        Integer collect = emps
                .stream()
                .map(Employee::getAge)
                .collect(Collectors.reducing(0, (x, y) -> x + y));

        System.out.println(collect);

    }

    @Test
    public void test13(){
        String collect = emps
                .stream()
                .map(Employee::getName)
                .collect(Collectors.joining("--", "!!", "**"));

        System.out.println(collect);
    }

    @Test
    public void test12(){
        Map<Boolean, List<Employee>> collect = emps
                .stream()
                .distinct()
//                .collect(Collectors.groupingBy(x -> x.getAge()));
                .collect(Collectors.partitioningBy(x -> x.getSalary() < 5000));

    }

    @Test
    public void test11(){
        DoubleSummaryStatistics collect = emps
                .stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));

        double max = collect.getMax();
        System.out.println(max);
    }


    @Test
    public void test10(){
        Optional<Integer> collect = emps
                .stream()
                .map(Employee::getAge)
                .collect(Collectors.maxBy(Integer::compareTo));

        System.out.println(collect.get());
    }

    @Test
    public void test9(){
        HashSet<String> collect = emps
                .stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Test
    public void test1() {
        Stream<Employee> stream = emps.stream();

        Stream<Integer> stream1 = Arrays.stream(new Integer[10]);

        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4);

        Stream.generate(Math::random).limit(10).forEach(System.out::println);

        Stream.iterate(0, x -> x + 2).limit(10).forEach(System.out::println);

    }

    @Test
    public void test2() {
        emps.stream()
                .skip(2)
                .filter(x -> x.getAge() > 20)
                .limit(1)
                .forEach(System.out::println);

        emps.parallelStream()
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void test3() {
        emps.stream()
                .distinct()
                .map(Employee::getName)
                .sorted()
                .forEach(System.out::println);
    }

    @Test
    public void test4() {

        List<String> strList = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");


        Stream<Stream<Character>> stream2 = strList.stream()
                .map(StreamAPITest::filterCharacter);

        stream2.forEach((sm) -> {
            sm.forEach(System.out::println);
        });

        System.out.println("---------------------------------------------");

        Stream<Character> stream3 = strList.stream()
                .flatMap(StreamAPITest::filterCharacter);

        stream3.forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();

        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }

        return list.stream();
    }

    @Test
    public void test5() {
        emps.stream()
                .distinct()
                .sorted((x, y) -> {
                    if (x.getAge() != y.getAge()) {
                        return Integer.compare(x.getAge(), y.getAge());
                    }
                    return x.getName().compareTo(y.getName());
                }).forEach(System.out::println);
    }

    @Test
    public void test6() {
        Optional<Employee> max = emps.stream()
//                .allMatch( x -> x.getAge()>10);
//        .anyMatch(x -> x.getName().contains("man"));
//        .noneMatch(x -> x.getSalary() > 10000);
//                .findFirst();

//        .findAny();
//.count();
                .max((x, y) -> Double.compare(x.getSalary(), y.getSalary()));

        max.ifPresent(Employee::getName);

        System.out.println(max);
    }

    @Test
    public void test7(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        Integer integer = list.stream()
                .reduce(0, Integer::sum);

        System.out.println(integer);

        Optional<Integer> reduce = list.stream()
                .reduce(Integer::sum);

        System.out.println(reduce);
    }

    @Test
    public void test8(){
//        emps.stream()
//                .map(Employee::getName)
//                .
//
    }

}
