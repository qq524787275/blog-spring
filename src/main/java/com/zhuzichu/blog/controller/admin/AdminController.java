package com.zhuzichu.blog.controller.admin;

import com.zhuzichu.blog.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.security.provider.MD5;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

private Logger logger = LoggerFactory.getILoggerFactory().getLogger(this.getClass().getName());
    @RequestMapping(value = "/login" )
    public String login(){
            return "admin/login";
    }

    @RequestMapping(value = "/index")
    public String index(){
        return "admin/index";
    }
}
