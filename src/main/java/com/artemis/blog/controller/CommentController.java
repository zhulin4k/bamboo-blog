package com.artemis.blog.controller;

import com.artemis.blog.entity.Comment;
import com.artemis.blog.entity.Result;
import com.artemis.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 俞周凯
 * @CreateTime: 2024-12-04
 * @Description:
 */
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/add")
    public Result<Comment> addComment(@RequestBody Comment comment) {
        Result<Comment> result = new Result<>();
        int success = commentService.addComment(comment);
        if (success == 1) {
            result.setMessage("添加成功");
            result.setSuccess(true);
            result.setData(comment);
        } else {
            result.setMessage("添加失败");
            result.setSuccess(false);
            result.setData(null);
        }
        return result;
    }

    @RequestMapping("/all/{blogId}")
    public Result<List<Comment>> getAllCommentsOfBlog(@PathVariable long blogId) {
        Result<List<Comment>> result = new Result<>();
        result.setData(commentService.getAllCommentsByBlogId(blogId));
        result.setSuccess(true);
        result.setMessage("获取成功");
        return result;
    }

    @RequestMapping("/delete/{id}")
    public Result<Comment> deleteComment(@PathVariable int id) {
        Result<Comment> result = new Result<>();
        int success = commentService.deleteComment(id);
        if (success == 1) {
            result.setCode(200);
            result.setMessage("删除成功");
            result.setSuccess(true);
            result.setData(null);
        } else {
            result.setMessage("删除失败");
            result.setSuccess(false);
            result.setData(null);
        }
        return result;
    }
}
