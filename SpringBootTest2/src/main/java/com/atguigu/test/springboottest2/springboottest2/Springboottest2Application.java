package com.atguigu.test.springboottest2.springboottest2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;


@ComponentScan(value = "com.atguigu")
@MapperScan(value = "com.atguigu.mapper")
@SpringBootApplication
public class Springboottest2Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboottest2Application.class, args);
    }

}
