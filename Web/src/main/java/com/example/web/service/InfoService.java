package com.example.web.service;

import com.example.web.enm.Message;
import com.example.web.entity.EmployInfo;
import com.example.web.entity.Employee;
import com.example.web.entity.Result;
import com.example.web.repository.EmployeeRepository;
import com.example.web.repository.InfoRepository;
import com.example.web.utils.ResultUtils;
import com.mysql.jdbc.util.ResultSetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;

/**
 * @Author Memory
 * @Date 2021/7/12 10:57
 * @Version 1.0
 */
@Service
public class InfoService {

    @Autowired
    InfoRepository infoRepository;
    EmployeeRepository employeeRepository;

    /**
     * 添加员工评价
     * @param employInfo
     * @return
     */
    public Result add(EmployInfo employInfo){
        return ResultUtils.success(infoRepository.save(employInfo));
    }

    /**
     * 查询处于离职状态的某员工在以往公司的评价信息
     * @param name
     * @return
     */
    public Result query(String name){
        Employee employee=employeeRepository.findByName(name);
        if(employee!=null){
            return ResultUtils.error(Message.EMPLOY_IN_JOB);
        }
        return ResultUtils.success(infoRepository.findByName(name));
    }
}
