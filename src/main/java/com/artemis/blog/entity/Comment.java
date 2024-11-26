package com.artemis.blog.entity;

import lombok.Data;

/**
 * @Author: 俞周凯
 * @CreateTime: 2024-11-24
 * @Description:
 */
@Data
public class Comment {
    private String id;
    private String blogId;
    private String userId;
    private String content;
    private String createTime;
    private String parentId;
}
