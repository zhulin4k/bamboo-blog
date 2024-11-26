package com.artemis.blog.service;

import com.artemis.blog.entity.User;
import com.artemis.blog.mapper.UserMapper;
import com.artemis.blog.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * @Author: 俞周凯
 * @CreateTime: 2024-11-24
 * @Description:
 */

public class UserServiceTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterSuccess() {
        // mock h2 cant find this user
        when(userMapper.findByUsername("new_username")).thenReturn(null);

        User newUser = new User();
        newUser.setUsername("new_username");
        newUser.setPassword("password");

        boolean result = userService.register(newUser);
        assertTrue(result);
        verify(userMapper, times(1)).insertUser(newUser); // 确保调用了插入方法
    }

    @Test
    void testRegisterFailure() {
        when(userMapper.findByUsername("new_username")).thenReturn(new User());

        User newUser = new User();
        newUser.setUsername("new_username");
        newUser.setPassword("password");

        boolean result = userService.register(newUser);
        assertFalse(result);
        verify(userMapper, never()).insertUser(any());
    }

    @Test
    void testLoginSuccess() {
        User user = new User();
        user.setUsername("valid_name");
        user.setPassword("correct_password");
        when(userMapper.findByUsername("valid_name")).thenReturn(user);

        User result = userService.login("valid_name", "correct_password");
        assertNotNull(result);
        assertEquals("valid_name", result.getUsername());
    }

    @Test
    void testLoginFailure() {
        // 模拟数据库查不到用户
        when(userMapper.findByUsername("invalid_user")).thenReturn(null);

        User result = userService.login("invalid_user", "wrong_password");

        // 验证结果
        assertNull(result);
    }
}
