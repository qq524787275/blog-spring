package com.zhuzichu.blog.dao;

import com.zhuzichu.blog.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component("userMapper")
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

    User selectByUsername(String username);
}