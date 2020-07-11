package com.atguigu.test.service;

import com.atguigu.test.mapper.UserMapper;
import com.atguigu.test.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-01 12:37
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int registUser(User user) {
        int insert = userMapper.insert(user);

        return insert;
    }
}
