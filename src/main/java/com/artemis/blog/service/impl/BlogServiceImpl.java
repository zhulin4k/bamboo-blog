package com.artemis.blog.service.impl;

import com.artemis.blog.entity.Blog;
import com.artemis.blog.mapper.BlogMapper;
import com.artemis.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 俞周凯
 * @CreateTime: 2024-11-24
 * @Description:
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public boolean createBlog(Blog blog) {
        return blogMapper.insertBlog(blog) > 0;
    }

    @Override
    public List<Blog> getAllBlogs(int offset, int limit) {
        return blogMapper.findAllBlogs(offset, limit);
    }

    @Override
    public Blog getBlogById(Integer id) {
        return blogMapper.findBlogById(id);
    }

    @Override
    public boolean updateBlog(Blog blog) {
        Blog existingBlog = blogMapper.findBlogById(blog.getId());
        if (existingBlog == null) {
            throw new RuntimeException("博客不存在");
        }
        // 合并更新字段
        blog.setUserId(existingBlog.getUserId());

        return blogMapper.updateBlog(blog) > 0;
    }

    @Override
    public boolean deleteBlog(Integer id) {
        return blogMapper.deleteBlogById(id) > 0;
    }
}
