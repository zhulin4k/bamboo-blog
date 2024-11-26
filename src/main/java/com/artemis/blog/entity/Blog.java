package com.artemis.blog.entity;

/**
 * @Author: 俞周凯
 * @CreateTime: 2024-11-24
 * @Description:
 */

import lombok.Data;

import java.util.List;
@Data
public class Blog {
    private Integer id;
    private Integer userId;
    private String title;
    private String content;
    private Integer categoryId;
    private String createTime;
    private String modifiedTime;
    private Boolean isDeleted;
}

