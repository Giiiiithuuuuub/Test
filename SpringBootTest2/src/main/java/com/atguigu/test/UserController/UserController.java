package com.atguigu.test.UserController;

import com.atguigu.test.UserService.UserService;
import com.atguigu.test.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-01 13:28
 */

@Controller
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/regist")
    @ResponseBody
    public String doRegist(User user){

        System.out.println("user =" + user);
        int i = userService.registUser(user);
        if (i == 1){
            return "regist success";
        }
        return "regist wrong";

    }
}
