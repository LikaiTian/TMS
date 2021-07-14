package com.example.web.entity;

import javax.persistence.*;

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

    @Column(columnDefinition = "varchar(255) default ''")
    private String name;    //员工名字

    @Column(columnDefinition = "varchar(255) default ''")
    private String companyName;     //员工所在公司

    @Column(columnDefinition = "varchar(255) default ''")
    private String workStatus;      //员工工作状态（打分）

    @Column(columnDefinition = "varchar(2555) default ''")
    private String comment;         //hr对员工的评价

    @Column(columnDefinition = "varchar(255) default ''")
    private String fileAddress;     //关于员工的工资等各种信息的文件存放的地址
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

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }
}
