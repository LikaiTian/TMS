package com.example.web.controller;

import com.example.web.entity.Result;
import com.example.web.entity.User;
import com.example.web.repository.UserRepository;
import com.example.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
     * 注册功能
     * @param user
     * @return
     */
    @RequestMapping(value="/register",method = RequestMethod.POST)
    public Result register(User user){
        return userService.register(user);
    }
    /*public User register(@RequestParam(defaultValue = "新用户",required = false) String nickname,
                           @RequestParam(required = true) String companyName,
                           @RequestParam(required = true) String phone,
                           @RequestParam(required = true) String email,
                           @RequestParam(required = true) String password){
        //同一个电话只能注册一次
        User user0=userRepository.findByPhone(phone);
        User user=new User();
        if(user0!=null){
            return user;
        }
        user.setNickname(nickname);
        user.setCompanyName(companyName);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return user;
    }*/

    /**
     * 登录功能
     * @param phone
     * @param password
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public Result login(HttpServletRequest request,String phone, String password){
        return userService.login(request,phone,password);
    }
}
