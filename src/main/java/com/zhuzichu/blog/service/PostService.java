package com.zhuzichu.blog.service;

import com.github.pagehelper.PageHelper;
import com.zhuzichu.blog.dao.PostMapper;
import com.zhuzichu.blog.model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("postService")
public class PostService {
    private Logger logger = LoggerFactory.getILoggerFactory().getLogger(this.getClass().getName());
    @Autowired
    private PostMapper postMapper;

    public Post findPostById(int id){
        return postMapper.selectByPrimaryKey(id);
    }

    public List<Post> findAllPost(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return postMapper.selectAll();
    }
}
