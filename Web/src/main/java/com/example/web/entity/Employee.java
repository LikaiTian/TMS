package com.example.web.entity;

import javax.persistence.*;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author Memory
 * @Date 2021/7/9 16:35
 * @Version 1.0
 */
@Entity     //声明表的实体
@Table(name="employees")     //修改表的名字
public class Employee {
    @Id //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键生成策略
    private Integer id;
    private String name;
    private String idNum;
    private String sex;
    private Date birth;
    private Integer age;
    private String phone;
    private String company;
    private String job;
    private Integer state;  //有工作为1，无工作为0

    public Employee(){}
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

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        Calendar cal = Calendar.getInstance();
        this.age = cal.get(Calendar.YEAR)-birth.getYear();
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
