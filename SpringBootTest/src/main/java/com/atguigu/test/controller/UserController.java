package com.atguigu.test.controller;

import com.atguigu.test.service.UserService;
import com.atguigu.test.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Yu HaiFeng
 * @Description   控制层，用户处理客户端的请求， 以及给客户端完成响应
 * @create 2020-05-01 12:21
 */

@Controller//该注解用来标注，这是一个控制层
public class UserController {
    @Autowired
    private UserService userService;

    /*
     * @RequestMapping("/regist") ： 将客户端的 regist请求映射到当前方法
     *
     * @ResponseBody 将方法的返回通过json的格式返回给客户端
     *
     * 方法的User参数用来接收客户端提交的参数，只需要保证客户端的参数名与User的属性名一致，
     * 就能直接将客户端提交的参数封装到user对象中
     */

    @RequestMapping("/regist")
    @ResponseBody
    public String doRegist(User user){
        System.out.println("user = " +user);
        int i = userService.registUser(user);
        if (i == 1){
            return "regist success";
        }
        return "regist error";

    }
}
