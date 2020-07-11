package com.atguigu.java8;

import com.atguigu.java8.Exer1.Employee;
import org.junit.Test;

import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-28 20:29
 */
public class StreamTest2 {

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
    public void test1(){
        Map<Double, Map<String, List<Employee>>> collect = emps.stream()
                .collect(Collectors.groupingBy(Employee::getSalary, Collectors.groupingBy((x) -> {
                    if (x.getAge() >= 50)
                        return "laonian";
                    else if (x.getAge() >= 35)
                        return "zhognian";
                    else
                        return "chengnian";
                })));

    }

    @Test
    public void test2(){
        long reduce = LongStream.rangeClosed(0, 10000000000L)
                .parallel()
                .reduce(0, Long::sum);

        System.out.println(reduce);
    }

    @Test
    public void test3(){
        Integer collect = emps.stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), List::size));

        System.out.println(collect);
    }

    @Test
    public void test4(){
        Spliterator<Employee> spliterator = emps.stream()
                .spliterator();

//        while (spliterator.)
    }
}
