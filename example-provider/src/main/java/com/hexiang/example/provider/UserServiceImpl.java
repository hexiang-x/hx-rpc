package com.hexiang.example.provider;

import com.hexiang.example.common.model.User;
import com.hexiang.example.common.service.UserService;

import java.sql.SQLOutput;

/**
 * common包中，UserService接口实现类
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.println("hexiang");
        return user;
    }
}
