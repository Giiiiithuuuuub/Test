package printftest;

import org.junit.Test;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-10 21:36
 */
public class PrintfTest {
    @Test
    public void test(){
        int i = 10;
        double j = 1.2;

        System.out.printf("%d,%05d,%f" , i , i ,j);
    }

    @Test
    public void test1(){
        String str = "abcdefg";

        System.out.printf("%-5.3s",str);
    }

    @Test
    public void test2(){
        double db = 1.234353453;

        System.out.printf("%-5.2f",db);
    }

    @Test
    public void test3(){
        System.out.println("aaabbb");
    }

}
