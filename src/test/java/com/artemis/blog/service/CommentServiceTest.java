package com.artemis.blog.service;

import com.artemis.blog.entity.Comment;
import com.artemis.blog.mapper.CommentMapper;
import com.artemis.blog.service.impl.CommentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @Author: 俞周凯
 * @CreateTime: 2024-12-04
 * @Description:
 */

public class CommentServiceTest {
    @Mock
    private CommentMapper commentMapper;

    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCommentSuccess() {
        Comment comment = new Comment();
        comment.setBlogId(1);
        comment.setContent("this is a comment");
        comment.setUserId(1);
        comment.setParentId(0);

        when(commentMapper.insertComment(comment)).thenReturn(1);
        int result = commentService.addComment(comment);

        assertEquals(1, result);
    }

    @Test
    void testAddCommentFailure() {
        // when object is null
        int resultNull = commentService.addComment(null);
        assertEquals(-1, resultNull);

        // when content is null
        Comment comment = new Comment();
        comment.setContent(null);
        comment.setBlogId(1L);
        comment.setParentId(0L);

        int resultContentNull = commentService.addComment(comment);
        assertEquals(-1, resultContentNull);

        // when blogId is wrong
        Comment commentNull = new Comment();
        commentNull.setContent("this is a comment");
        commentNull.setBlogId(-1);
        commentNull.setParentId(0L);

        int resultBlogIdNull = commentService.addComment(commentNull);
        assertEquals(-1, resultBlogIdNull);
    }

    // 测试根据博客ID显示评论时ID有效
    @Test
    void testGetAllCommentsByBlogId_ValidId() {
        long blogId = 1L;
        when(commentMapper.findCommentsByBlogId(blogId)).thenReturn(List.of(new Comment()));

        List<Comment> comments = commentService.getAllCommentsByBlogId(blogId);
        assertNotNull(comments); // 预期返回非空列表
    }

    // 测试根据博客ID显示评论时ID为负值
    @Test
    void testGetAllCommentsByBlogId_NegativeId() {
        List<Comment> comments = commentService.getAllCommentsByBlogId(-1L);
        assertNull(comments); // 预期返回值为null
    }

    // 测试删除评论时ID有效
    @Test
    void testDeleteComment_ValidId() {
        long commentId = 1L;
        when(commentMapper.deleteCommentById(commentId)).thenReturn(1);

        int result = commentService.deleteComment(commentId);
        assertEquals(1, result); // 预期返回值为1
    }

    // 测试删除评论时ID为负值
    @Test
    void testDeleteComment_NegativeId() {
        int result = commentService.deleteComment(-1L);
        assertEquals(-1, result); // 预期返回值为-1
    }

}
