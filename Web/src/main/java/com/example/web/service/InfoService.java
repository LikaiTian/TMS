package com.example.web.service;

import com.example.web.entity.EmployInfo;
import com.example.web.entity.Result;
import com.example.web.repository.InfoRepository;
import com.example.web.utils.ResultUtils;
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

    /**
     * 添加员工评价
     * @param employInfo
     * @return
     */
    public Result add(EmployInfo employInfo){
        return ResultUtils.success(infoRepository.save(employInfo));
    }
}
