package com.atguigu.java8.Exer2;

import com.atguigu.java8.Exer1.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-29 1:07
 */
public class StreamTest {

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
    public void test1(){
        int[] arr = {1,2,3,4,5};

        Arrays.stream(arr)
                .map(x -> x*x)
                .forEach(System.out::println);
    }

    @Test
    public void test2(){
//        long count = emps
//                .stream()
//                .count();
//        System.out.println(count);

        Optional<Integer> reduce = emps
                .stream()
                .map(x -> 1)
                .reduce(Integer::sum);

        System.out.println(reduce.get());
    }
}
