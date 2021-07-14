package com.example.web.service;

import com.example.web.entity.Company;
import com.example.web.entity.Result;
import com.example.web.repository.CompanyRepository;
import com.example.web.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Memory
 * @Date 2021/7/12 15:09
 * @Version 1.0
 */
@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    /**
     * 编辑公司简介
     * @param company
     * @return
     */
    public Result add(Company company){

        Company company0=companyRepository.findByCompanyName(company.getCompanyName());
        if(company0==null){
            return ResultUtils.success(companyRepository.save(company));
        }
        company0.setCompanyName(company.getCompanyName());
        company0.setCompanyInfo(company.getCompanyInfo());
        return ResultUtils.success(companyRepository.save(company0));
    }
    /*public Result edit(Company company){
        return ResultUtils.success(companyRepository.s)
    }*/

    /**
     * 查到公司简介
     * @param companyName
     * @return
     */
    public Result getInfo(String companyName){
        return ResultUtils.success(companyRepository.findByCompanyName(companyName));
    }
}
