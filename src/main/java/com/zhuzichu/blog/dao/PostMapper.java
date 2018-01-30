package com.zhuzichu.blog.dao;

import com.zhuzichu.blog.model.Post;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("postMapper")
public interface PostMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);

    List<Post> selectAll();
}