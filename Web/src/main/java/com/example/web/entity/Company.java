package com.example.web.entity;

import javax.persistence.*;

/**
 * @Author Memory
 * @Date 2021/7/12 14:52
 * @Version 1.0
 */
@Entity

public class Company {

    @Id //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键生成策略
    private Integer id;

    @Column(columnDefinition = "varchar(255) default ''")
    private String companyName;

    @Column(columnDefinition = "varchar(2550) default ''")
    private String companyInfo;
    public Company(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }
}
