package com.artemis.blog.entity;

import lombok.Data;

/**
 * @Author: 俞周凯
 * @CreateTime: 2024-11-24
 * @Description:
 */
@Data
public class User {
    private String id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String avatar;
    private String createTime;
    private String extra; // 用于扩展字段
}
