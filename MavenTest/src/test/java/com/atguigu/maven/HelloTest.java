package com.atguigu.maven;

import org.junit.Test;
import static junit.framework.Assert.*;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-20 0:25
 */
public class HelloTest {
    @Test
    public void testHello(){
        Hello hello = new Hello();
        String results = hello.sayHello("atguigu");
        assertEquals("Hello atguigu!",results);
    }

}
