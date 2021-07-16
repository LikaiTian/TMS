package com.example.web.controller;

import com.example.web.entity.Result;
import com.example.web.entity.User;
import com.example.web.repository.UserRepository;
import com.example.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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
    @RequestMapping(value="/register",method = RequestMethod.GET)
    public Result register(User user){
        return userService.register(user);
    }

    /**
     * 登录
     * @param
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public Result login(HttpServletRequest request, User user){
        /*User user0 = userService;
        user.setPassword(password);
        user.setPhone(phone);
        System.out.println(user.toString());//new*/
        return userService.login(request,user);
    }

    /**
     * 修改个人信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/modify",method = RequestMethod.GET)
    public Result UpdateOne(User user){
        return userService.UpdateOne(user);
    }

    /**
     * 注销账户
     * @param user
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Result deleteOne(User user){
        return userService.deleteOne(user);
    }

    /**
     * 根据id获取个人信息
     * @param id
     * @return
     */
    @RequestMapping(value ="/getMessage",method = RequestMethod.GET)
    public User findOne(Integer id){
        return userService.findOne(id);
    }

    /**
     * 修改头像
     * @param id
     * @param file
     * @return
     */
    @RequestMapping(value = "/getImg",method = RequestMethod.POST)
    public Result getImg(Integer id,MultipartFile file){
        return userService.fileUpload(id,file);
    }
}
