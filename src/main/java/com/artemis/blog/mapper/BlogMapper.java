package com.artemis.blog.mapper;

import com.artemis.blog.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;

import java.util.List;

/**
 * @Author: 俞周凯
 * @CreateTime: 2024-11-24
 * @Description:
 */
@Mapper
public interface BlogMapper {

    int insertBlog(Blog blog);

    List<Blog> findAllBlogs(@Param("offset") int offset, @Param("limit") int limit);

    Blog findBlogById(@Param("id") Integer id);

    int updateBlog(Blog blog);

    int deleteBlogById(@Param("id") Integer id);

}
