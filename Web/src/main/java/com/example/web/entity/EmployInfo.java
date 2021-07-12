package com.example.web.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author Memory
 * @Date 2021/7/9 16:53
 * @Version 1.0
 */
@Entity     //声明表的实体
public class EmployInfo {

    @Id //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键生成策略
    private Integer id;
    private String name;
    private String companyName;
    private String workStatus;
    private String comment;
    public EmployInfo(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
