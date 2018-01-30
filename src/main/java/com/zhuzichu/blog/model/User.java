package com.zhuzichu.blog.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
    private Integer id;

    @NotNull
    @Size(min = 1,max = 10)
    private String username;

    @NotNull
    @Size(min = 5,max = 10)
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}