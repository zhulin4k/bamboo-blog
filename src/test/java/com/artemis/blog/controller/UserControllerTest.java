package com.artemis.blog.controller;

import com.artemis.blog.entity.User;
import com.artemis.blog.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testRegisterSuccess() throws Exception {
        User user = new User();
        user.setUsername("new_user");
        user.setPassword("password");

        when(userService.register(any(User.class))).thenReturn(true);

        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"new_user\", \"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("注册成功"));

        verify(userService, times(1)).register(any(User.class));
    }

    @Test
    void testRegisterFailure() throws Exception {
        when(userService.register(any(User.class))).thenReturn(false);

        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"existing_user\", \"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("用户名已存在"));

        verify(userService, times(1)).register(any(User.class));
    }

    @Test
    void testLoginSuccess() throws Exception {
        User user = new User();
        user.setUsername("valid_user");
        user.setPassword("correct_password");

        when(userService.login("valid_user", "correct_password")).thenReturn(user);

        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"valid_user\", \"password\":\"correct_password\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("登录成功"));

        verify(userService, times(1)).login("valid_user", "correct_password");
    }

    @Test
    void testLoginFailure() throws Exception {
        when(userService.login("invalid_user", "wrong_password")).thenReturn(null);

        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"invalid_user\", \"password\":\"wrong_password\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("用户名或密码错误"));

        verify(userService, times(1)).login("invalid_user", "wrong_password");
    }
}

