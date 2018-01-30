package com.zhuzichu.blog.api;

import com.zhuzichu.blog.model.BaseModel;
import com.zhuzichu.blog.model.User;
import com.zhuzichu.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/user")
public class UserApi {
    private Logger logger = LoggerFactory.getILoggerFactory().getLogger(this.getClass().getName());
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public BaseModel<User> login(HttpServletResponse response,@RequestParam(value = "username",required = true)String username, @RequestParam(value = "password",required = true)String password){
        User user= userService.getUserByUsernameAndPassword(username, password);
        BaseModel<User> model=new BaseModel<>();

        if(response!=null){
            logger.info("有数据");
        }
        
        if(user==null){
            model.msg="登录失败";
            model.status=-1;
        }else{
            model.msg="登录成功";
            model.status=1;
            model.result=user;
        }
        return model;
    }
}
