package com.atguigu.test.UserService;

import com.atguigu.test.beans.User;
import org.springframework.stereotype.Service;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-01 13:31
 *
 */

@Service
public interface UserService {

    int registUser(User user);
}
