package com.example.web.controller;

import com.example.web.entity.Company;
import com.example.web.entity.Result;
import com.example.web.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Memory
 * @Date 2021/7/12 15:23
 * @Version 1.0
 */
@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    /**
     * 根据公司名字查找公司简介
     * @param companyName
     * @return
     */
    @RequestMapping(value = "getInfo",method = RequestMethod.GET)
    public Result getInfo(String companyName){
        return companyService.getInfo(companyName);
    }

    /**
     * 编辑公司简介
     * @param company
     * @return
     */
    @RequestMapping(value = "/modify",method = RequestMethod.GET)
    public Result modify(Company company){

        return companyService.add(company);
    }
}
