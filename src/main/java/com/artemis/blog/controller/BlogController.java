package com.artemis.blog.controller;

import com.artemis.blog.entity.Blog;
import com.artemis.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 俞周凯
 * @CreateTime: 2024-11-24
 * @Description:
 */
@RestController
@RequestMapping("/api/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @PostMapping("/new")
    public String createBlog(@RequestBody Blog blog) {
        blog.setUserId(1); // 单用户时固定为当前用户
        return blogService.createBlog(blog) ? "博客创建成功" : "博客创建失败";
    }

    @GetMapping("/all")
    public List<Blog> getAllBlogs(@RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        return blogService.getAllBlogs(page, size);
    }

    @GetMapping("/{id}")
    public Blog getBlogById(@PathVariable Integer id) {
        return blogService.getBlogById(id);
    }

    @PutMapping("/{id}")
    public String updateBlog(@PathVariable Integer id, @RequestBody Blog blog) {
        blog.setId(id);
        return blogService.updateBlog(blog) ? "博客更新成功" : "博客更新失败";
    }

    @DeleteMapping("/{id}")
    public String deleteBlog(@PathVariable Integer id) {
        return blogService.deleteBlog(id) ? "博客删除成功" : "博客删除失败";
    }
}
