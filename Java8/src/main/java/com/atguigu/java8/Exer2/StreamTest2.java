package com.atguigu.java8.Exer2;

import org.junit.Before;
import org.junit.Test;
import sun.text.resources.cldr.ii.FormatData_ii;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-29 1:13
 */
public class StreamTest2 {

    private List<Transaction> transactions = null;

    @Before
    public void before(){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    @Test
    public void test1(){
        transactions
                .stream()
                .filter(x -> x.getYear() == 2011)
                .sorted((x,y) -> Integer.compare(x.getValue(),y.getValue()))
                .forEach(System.out::println);
    }

    @Test
    public void test2(){
        Set<String> collect = transactions
                .stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .collect(Collectors.toSet());

        System.out.println(collect);
    }

    @Test
    public void test3(){
        transactions
                .stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(x -> x.getCity().equals("Cambridge"))
                .sorted((x,y)->x.getName().compareTo(y.getName()))
                .forEach(System.out::println);

    }

    @Test
    public void test4(){
        String collect = transactions
                .stream()
                .map(Transaction::getTrader)
                .distinct()
                .map(Trader::getName)
                .collect(Collectors.joining());

        char[] chars = collect.toCharArray();

        for (int i = 0; i < chars.length - 1; i++) {
            for (int j = 0; j < chars.length - 1 - i; j++) {
                if (chars[j] > chars[j + 1]){
                    char temp = chars[j];
                    chars[j] = chars[j + 1];
                    chars[j + 1] = temp;
                }
            }
        }

        System.out.println(chars);
    }

    @Test
    public void test5(){
        boolean milan = transactions
                .stream()
                .map(Transaction::getTrader)
                .anyMatch(x -> x.getCity().equals("Milan"));
        System.out.println(milan);
    }

    @Test
    public void test6(){
        Optional<Integer> cambridge = transactions
                .stream()
                .filter(x -> x.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(Integer::sum);

        System.out.println(cambridge.get());
    }

    @Test
    public void test7(){
        Optional<Integer> max = transactions
                .stream()
                .map(Transaction::getValue)
                .max(Integer::compare);

        System.out.println(max);
    }

    @Test
    public void test8(){
        Optional<Transaction> first = transactions
                .stream()
                .sorted((x, y) -> Integer.compare(x.getValue(), y.getValue()))
                .findFirst();

        System.out.println(first.get());
    }
}
