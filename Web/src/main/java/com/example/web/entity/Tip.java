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
    private Integer userId;     //产生该消息的hr的id

    @Column(columnDefinition = "varchar(255) default ''")
    private String time;        //该消息产生的时间

    @Column(columnDefinition = "varchar(255) default ''")
    private String message;     //该消息的具体内容

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
