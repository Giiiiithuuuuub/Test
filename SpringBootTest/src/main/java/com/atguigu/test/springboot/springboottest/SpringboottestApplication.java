package com.atguigu.test.springboot.springboottest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(value = "com.atguigu.mapper")
@ComponentScan(value = "com.atguigu")
@SpringBootApplication
public class SpringboottestApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringboottestApplication.class, args);
    }

}
