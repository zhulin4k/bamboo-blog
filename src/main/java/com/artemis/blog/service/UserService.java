package com.artemis.blog.service;

import com.artemis.blog.entity.User;

/**
 * @Author: 俞周凯
 * @CreateTime: 2024-11-24
 * @Description:
 */

public interface UserService {
    boolean register(User user);
    User login(String username, String password);
}
