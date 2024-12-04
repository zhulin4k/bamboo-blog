package com.artemis.blog.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: 俞周凯
 * @CreateTime: 2024-11-24
 * @Description:
 */
@Data
public class Comment {
    private long id;
    private long blogId;
    private long userId;
    private String content;
    private LocalDateTime createTime;
    private long parentId;
}
