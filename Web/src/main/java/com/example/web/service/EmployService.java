package com.example.web.service;

import com.example.web.entity.Employee;
import com.example.web.entity.Result;
import com.example.web.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Memory
 * @Date 2021/7/9 17:11
 * @Version 1.0
 */
@Service
public class EmployService {
    @Autowired
    EmployeeRepository employeeRepository;

    /*public Result add(Employee employee){
        Employee employee1=employeeRepository.findByIdNum(employee.getIdNum());
        if(employee1==null){

        }
    }*/
}
