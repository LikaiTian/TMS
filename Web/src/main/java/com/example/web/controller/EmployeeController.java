package com.example.web.controller;

import com.example.web.entity.Employee;
import com.example.web.entity.Result;
import com.example.web.service.EmployService;
import com.example.web.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Memory
 * @Date 2021/7/11 14:13
 * @Version 1.0
 */
@RestController
@RequestMapping("/employ")
public class EmployeeController {
    @Autowired
    EmployService employService;

    /**
     * 新增一个员工
     * @param employee
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public Result add(Employee employee){
        return employService.addOne(employee);
    }

    /**
     * 更新一个员工信息
     * @param employee
     * @return
     */
    @RequestMapping(value = "/modify",method = RequestMethod.GET)
    public Result update(Employee employee){
        return employService.updateOne(employee);
    }

    /**
     * 辞退一个员工
     * @param employee
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Result delete(Employee employee){
        return employService.deleteOne(employee);
    }

    /**
     * 根据公司查找员工
     * @param company
     * @return
     */
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public Result findAll(String company){
        return employService.findByCompany(company);
    }

    /**
     * 根据公司部门查找员工信息
     * @param company
     * @param department
     * @return
     */
    @RequestMapping(value = "/findByDepartment",method = RequestMethod.GET)
    public Result findByCompanyAndDepartment(String company,String department){
        return employService.findByCompanyAndDepartment(company, department);
    }
}
