package com.artemis.blog.service.impl;

import com.artemis.blog.entity.Comment;
import com.artemis.blog.mapper.CommentMapper;
import com.artemis.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 俞周凯
 * @CreateTime: 2024-12-03
 * @Description:
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int addComment(Comment comment) {
        // check if comment is valid
        if (comment == null || comment.getContent() == null) {
            return -1;
        }
        // check if blogId is valid and blog exists
        if (comment.getBlogId() < 0) {
            return -1;
        }
        // check if parentId is valid
        if (comment.getParentId() < 0) {
            return -1;
        }
        return commentMapper.insertComment(comment);
    }

    @Override
    public List<Comment> getAllCommentsByBlogId(long blogId) {
        if (blogId < 0) {
            return null;
        }
        return commentMapper.findCommentsByBlogId(blogId);
    }

    @Override
    public int deleteComment(long id) {
        if (id < 0) {
            return -1;
        }
        return commentMapper.deleteCommentById(id);
    }
}
