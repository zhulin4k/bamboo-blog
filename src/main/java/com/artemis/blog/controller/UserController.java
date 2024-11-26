package com.artemis.blog.controller;

import com.artemis.blog.entity.User;
import com.artemis.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        boolean success = userService.register(user);
        return success ? "注册成功" : "用户名已存在";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User loggedInUser = userService.login(user.getUsername(), user.getPassword());
        return loggedInUser != null ? "登录成功" : "用户名或密码错误";
    }
}
