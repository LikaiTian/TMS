package com.example.web.controller;

import com.example.web.entity.Result;
import com.example.web.service.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Memory
 * @Date 2021/7/20 19:40
 * @Version 1.0
 */
@RestController
@RequestMapping("/tip")
public class TipController {
    @Autowired
    TipService tipService;

    /**
     * 添加操作信息
     * @param userId
     * @param message
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public Result add(String userId, String message){
        return tipService.add(userId, message);
    }

    /**
     * 根据用户id返回所有操作信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public Result query(String userId){
        return tipService.query(userId);
    }
}
