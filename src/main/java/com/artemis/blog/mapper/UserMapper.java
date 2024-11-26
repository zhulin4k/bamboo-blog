package com.artemis.blog.mapper;

import com.artemis.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: 俞周凯
 * @CreateTime: 2024-11-24
 * @Description:
 */

@Mapper
public interface UserMapper {
    int insertUser(User user);
    User findByUsername(String username);
}