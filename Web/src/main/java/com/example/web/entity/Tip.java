package com.example.web.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author Memory
 * @Date 2021/7/20 18:21
 * @Version 1.0
 */
@Entity     //声明表的实体
public class Tip {
    @Id //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键生成策略
    private Integer id;

    @Column(columnDefinition = "varchar(255) default ''")
    private Integer userId;

    @Column(columnDefinition = "varchar(255) default ''")
    private String time;

    @Column(columnDefinition = "varchar(255) default ''")
    private String message;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
