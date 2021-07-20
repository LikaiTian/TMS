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

    @Column(columnDefinition = "varchar(255) default ''")
    private String cardId;

    @Column(columnDefinition = "varchar(255) default ''",unique = true)
    private String name;

    @Column(columnDefinition = "varchar(255) default ''")
    private String sex;

    @Column(columnDefinition = "varchar(255) default ''")
    private String birth;
/*
    @Column(columnDefinition = "varchar(255) default ''")
    private Integer age;*/

    @Column(columnDefinition = "varchar(255) default ''")
    private String phone;

    @Column(columnDefinition = "varchar(255) default ''")
    private String company;

    @Column(columnDefinition = "varchar(255) default ''")
    private String department;

    @Column(columnDefinition = "varchar(255) default ''")
    private String job;

    @Column(columnDefinition = "varchar(255) default ''")
    private double salary;

/*
    @Column(columnDefinition = "varchar(255) default ''")
    private Integer state;  //有工作为1，无工作为0
*/

    public Employee(){}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    /*public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        Calendar cal = Calendar.getInstance();
        this.age = cal.get(Calendar.YEAR)-birth.getYear();
    }*/

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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

}
