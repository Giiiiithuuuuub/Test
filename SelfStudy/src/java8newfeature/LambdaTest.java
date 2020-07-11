package java8newfeature;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-25 7:57
 */
public class LambdaTest {

    List<Employee> emps = Arrays.asList(
            new Employee(101, "zhangsan", 18, 9999.99),
            new Employee(102, "lisi", 59, 6666.66),
            new Employee(103, "wangwu", 28, 3333.33),
            new Employee(104, "zhaoliu", 8, 7777.77),
            new Employee(105, "tianqi", 28, 5555.55)
    );

    @Test
    public void test1() {

        Collections.sort(emps, (x, y) -> {
            if (x.getAge() == y.getAge()){
                return x.getName().compareTo(y.getName());
            }else {
                return Integer.compare(x.getAge(),y.getAge());
            }
        });

        emps.forEach(System.out::println);
    }


    @Test
    public void test2(){
        String s = toChange("aadfadf", String::toUpperCase);
        System.out.println(s);

        String s1 = toChange("sdfsdfs",x -> x.substring(2,5));
    }

    public String toChange(String string, Function<String , String> function){
        return function.apply(string);
    }


    @Test
    public void test3(){
        Long aLong = toCalcu(100L, 200L, (x, y) -> x + y);
        System.out.println(aLong);
    }

    public Long toCalcu(Long l1,Long l2,BiFunction<Long,Long,Long> function){
        return function.apply(l1,l2);
    }

    @Test
    public void test4(){

        Function<Employee,String> function = new Function<Employee, String>() {
            @Override
            public String apply(Employee employee) {
                return employee.getName();
            }
        };

        emps.stream()
//                .map((x) -> x.getName())
//                .map(Employee::getName)
                .map(function)
                .forEach(System.out::println);
    }

    @Test
    public void test5(){
       Integer[] num = {1,2,3,4,5};

        Stream<Integer> sm = Arrays.stream(num)
                                   .map(x -> x * x);

    }

    @Test
    public void test6(){
        Optional<Integer> reduce = emps.stream()
                .map(x -> 1)
//                .reduce(0, (x, y) -> x + y);
                .reduce(Integer::sum);
        System.out.println(reduce.get());

    }
}
