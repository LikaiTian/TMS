package com.example.web.entity;

import java.io.Serializable;

/**
 * @Author Memory
 * @Date 2021/7/8 21:22
 * @Version 1.0
 */
public class Result<T> implements Serializable {
    private Integer code;   //编号
    private String message; //信息
    private T date;         //参数

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }
}
