package com.atguigu.java8.Exer1;

import com.atguigu.java8.Exer1.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-26 23:49
 */
public class Test2 {

    List<Employee> emps = Arrays.asList(
            new Employee(101, "zhangsan", 18, 9999.99),
            new Employee(102, "lisi", 59, 6666.66),
            new Employee(103, "wangwu", 28, 3333.33),
            new Employee(104, "zhaoliu", 8, 7777.77),
            new Employee(105, "tianqi", 28, 5555.55)
    );

    @Test
    public void testExer1(){
        emps.sort((x, y) -> {
            if (x.getAge() != y.getAge()) {
                return Integer.compare(x.getAge(), y.getAge());
            }

            return -x.getName().compareTo(y.getName());
        });

        emps.forEach(System.out::println);

    }
}
