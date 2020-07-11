package com.atguigu.java8;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-28 20:50
 */
public class ForkJoinTest {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Long invoke = forkJoinPool.invoke(new ForkJoin(0L, 10000000000L));

        System.out.println(invoke);
    }

}

class ForkJoin extends RecursiveTask<Long> {

    private Long start;
    private Long end;

    private static final Long THRESHOLE = 100000L;

    public ForkJoin(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {

        if (end - start <= THRESHOLE) {
            long sum = 0L;
            for (Long i = start; i < end; i++) {
                sum += i;
            }
            return sum;
        } else {
            Long middle = (end - start) / 2;

            ForkJoin left = new ForkJoin(start, middle);
            Long join = left.join();
            ForkJoin right = new ForkJoin(middle,end);
            Long join1 = right.join();

            return join + join1;
        }
    }
}
