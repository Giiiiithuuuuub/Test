package java8newfeature;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-26 9:19
 */
public class ForkJoinTest {
    public static void main(String[] args) {

        Instant start = Instant.now();
//        ForkJoinPool forkJoinPool = new ForkJoinPool();
//        ForkJoinTask<Long> task = new ForkJoing(0L,10000000000L);
//        Long sum = forkJoinPool.invoke(task);
//        System.out.println(sum);//16262，cup占用100%
//        long sum = 0;
//
//        for (Long i = 0L; i <= 10000000000L ; i++) {
//            sum += i;
//        }
//        System.out.println(sum);//23173，cpu占用18%

        //java8之后有现成的方法，避免复杂的逻辑，底层实现仍然是ForkJoin框架

        LongStream.rangeClosed(0L,10000000000L)//方法：生成一个连续的数,包括首位，而range方法是包括首但是不包括尾
                .parallel()//并行流；如果使用串行流，则是sequential()
                .reduce(0,Long::sum);//耗时739，cpu使用100%但是只有一下


        Instant end = Instant.now();
        System.out.println("耗费时间为：" + Duration.between(start,end).toMillis());
    }

}

class ForkJoing extends RecursiveTask<Long> {
    private Long start;
    private Long end;
    private static final Long THRESHOLD = 1000000L;

    public ForkJoing(Long start,Long end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD){
            long sum = 0;
            for (Long i = start; i <=end ; i++) {
                sum +=i;
            }
            return sum;
        }else {
            Long middle = (end + start)/2;//自定义拆分条件：一半一半拆
            ForkJoing left = new ForkJoing(start,middle);
            left.fork();//拆分成子任务，同时压入队列
            ForkJoing right = new ForkJoing(middle + 1,end);
            right.fork();

            return left.join() + right.join();//Fork之后的结果一个个的累加总和
        }
    }
}
