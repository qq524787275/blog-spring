package com.zhuzichu.blog.service;

import com.zhuzichu.blog.dao.UserMapper;
import com.zhuzichu.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
    @Autowired
    UserMapper userMapper;


    public User getUserByUsernameAndPassword(String username, String password) {
        return userMapper.selectByUsernameAndPassword(username,password);
    }

    public User getUserByUsername(String username){
        return  userMapper.selectByUsername(username);
    }
}
