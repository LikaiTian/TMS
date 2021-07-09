package com.example.web.controller;

import com.example.web.entity.Result;
import com.example.web.entity.User;
import com.example.web.repository.UserRepository;
import com.example.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author Memory
 * @Date 2021/7/7 16:35
 * @Version 1.0
 */

@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    UserService userService;
    //写接口
    //value 和 method

    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping(value="/register",method = RequestMethod.PUT)
    public Result register(@RequestBody User user){
        return userService.register(user);
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(@RequestBody User user){
        return userService.login(user);
    }
}
