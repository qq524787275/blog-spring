package com.zhuzichu.blog.model;

import java.io.Serializable;

public class BaseModel<T> implements Serializable {
    private static final long serialVersionUID = 5213230387175987834L;

    public int status;
    public String msg;
    public T result;

    @Override
    public String toString() {
        return "BaseResponse{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
