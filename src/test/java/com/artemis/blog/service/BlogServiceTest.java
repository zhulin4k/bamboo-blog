package com.artemis.blog.service;

import com.artemis.blog.entity.Blog;
import com.artemis.blog.mapper.BlogMapper;
import com.artemis.blog.service.impl.BlogServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * @Author: 俞周凯
 * @CreateTime: 2024-11-24
 * @Description:
 */

public class BlogServiceTest {
    @Mock
    private BlogMapper blogMapper;

    @InjectMocks
    private BlogServiceImpl blogService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBlogSuccess() {
        Blog testBlog = new Blog();
        testBlog.setTitle("test_title");
        testBlog.setContent("test_content");
        testBlog.setUserId(1);
        testBlog.setCategoryId(1);

        when(blogMapper.insertBlog(testBlog)).thenReturn(1);

        boolean result = blogService.createBlog(testBlog);
        assertTrue(result);
        verify(blogMapper, times(1)).insertBlog(testBlog);
    }

    @Test
    void testGetAllBlogs() {
        List<Blog> mockBlogs = Arrays.asList(
                new Blog() {{
                    setId(1);
                    setTitle("Blog 1");
                    setContent("Content 1");
                    setCategoryId(1);
                }},
                new Blog() {{
                    setId(2);
                    setTitle("Blog 2");
                    setContent("Content 2");
                    setCategoryId(2);
                }}
        );

        when(blogMapper.findAllBlogs(anyInt(), anyInt())).thenReturn(mockBlogs); // 模拟分页查询

        List<Blog> blogs = blogService.getAllBlogs(1, 10);

        System.out.println(blogs);

        assertNotNull(blogs);
        assertEquals(2, blogs.size());
        assertEquals("Blog 1", blogs.getFirst().getTitle());
        verify(blogMapper, times(1)).findAllBlogs(1, 10); // 验证 findAllBlogs 被调用一次
    }

    @Test
    void testGetBlogById() {
        Blog mockBlog = new Blog();
        mockBlog.setId(1);
        mockBlog.setTitle("Test Blog");
        mockBlog.setContent("This is a test blog.");
        mockBlog.setCategoryId(1);

        when(blogMapper.findBlogById(1)).thenReturn(mockBlog); // 模拟根据 ID 查询

        Blog blog = blogService.getBlogById(1);

        assertNotNull(blog);
        assertEquals("Test Blog", blog.getTitle());
        assertEquals("This is a test blog.", blog.getContent());
        verify(blogMapper, times(1)).findBlogById(1); // 验证 findBlogById 被调用一次
    }

    @Test
    void testUpdateBlog() {
        Blog blog = new Blog();
        blog.setId(1);
        blog.setTitle("Updated Blog");
        blog.setContent("Updated content.");
        blog.setCategoryId(2);

        when(blogMapper.updateBlog(blog)).thenReturn(1); // 模拟更新成功

        boolean result = blogService.updateBlog(blog);

        assertTrue(result);
        verify(blogMapper, times(1)).updateBlog(blog); // 验证 updateBlog 被调用一次
    }

    @Test
    void testDeleteBlog() {
        when(blogMapper.deleteBlogById(1)).thenReturn(1); // 模拟软删除成功

        boolean result = blogService.deleteBlog(1);

        assertTrue(result);
        verify(blogMapper, times(1)).deleteBlogById(1); // 验证 deleteBlog 被调用一次
    }
}
