package com.artemis.blog.service.impl;

import com.artemis.blog.entity.User;
import com.artemis.blog.mapper.UserMapper;
import com.artemis.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 俞周凯
 * @CreateTime: 2024-11-24
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean register(User user) {
        User existUser = userMapper.findByUsername(user.getUsername());
        if (existUser != null) {
            return false; // username already exist
        }
        userMapper.insertUser(user);
        return true;
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
