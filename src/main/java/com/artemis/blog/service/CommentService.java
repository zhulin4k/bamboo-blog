package com.artemis.blog.service;

import com.artemis.blog.entity.Comment;

import java.util.List;

/**
 * @Author: 俞周凯
 * @CreateTime: 2024-12-03
 * @Description:
 */

public interface CommentService {
    int addComment(Comment comment);

    List<Comment> showAllCommentsByBlogId(long blogId);

    int deleteComment(long id);
}
