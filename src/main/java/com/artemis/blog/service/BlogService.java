package com.artemis.blog.service;

import com.artemis.blog.entity.Blog;

import java.util.List;

/**
 * @Author: 俞周凯
 * @CreateTime: 2024-11-24
 * @Description:
 */

public interface BlogService {
    boolean createBlog(Blog blog);

    List<Blog> getAllBlogs(int offset, int limit);

    Blog getBlogById(Integer id);

    boolean updateBlog(Blog blog);

    boolean deleteBlog(Integer id);
}
