package com.example.web.entity;

/**
 * @Author Memory
 * @Date 2021/7/19 10:12
 * @Version 1.0
 */

/**
 * 作为统计部门数据的工具
 */
public class DepartmentSalary {
    int[] salary;       //该部门员工薪资分布情况
    double averSalary;  //该部门平均薪资

    public int[] getSalary() {
        return salary;
    }

    public void setSalary(int[] salary) {
        this.salary = salary;
    }

    public double getAverSalary() {
        return averSalary;
    }

    public void setAverSalary(double averSalary) {
        this.averSalary = averSalary;
    }

    public DepartmentSalary(int[] salary, double averSalary) {
        this.salary = salary;
        this.averSalary = averSalary;
    }
}
