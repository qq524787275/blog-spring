package com.zhuzichu.blog.api;

import com.zhuzichu.blog.model.Post;
import com.zhuzichu.blog.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostApi {
    private Logger logger = LoggerFactory.getILoggerFactory().getLogger(this.getClass().getName());
    @Autowired
    PostService postService;
    @RequestMapping(value = "/getPost",method = RequestMethod.POST)
    public Post getPost(@RequestParam(value = "id",required = true) int id){
       return postService.findPostById(id);
    }

    @RequestMapping(value = "/getAllPost",method = RequestMethod.POST)
    public List<Post> getAll(@RequestParam(value = "pageNum",required = true)int pageNum, @RequestParam(value = "pageSize",required = true)int pageSize){
        logger.info("pageNum="+pageNum+",pageSize="+pageSize);
        return postService.findAllPost(pageNum,pageSize);
    }
}
