package com.example.web.entity;

/**
 * @Author Memory
 * @Date 2021/7/19 14:32
 * @Version 1.0
 */
public class CompanySalary {
    int[] employees;    //各部门人员分布
    double[] salary;    //各部门薪资对比情况

    public int[] getEmployees() {
        return employees;
    }

    public void setEmployees(int[] employees) {
        this.employees = employees;
    }

    public double[] getSalary() {
        return salary;
    }

    public void setSalary(double[] salary) {
        this.salary = salary;
    }
}
