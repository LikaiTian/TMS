package com.example.web.controller;

import com.example.web.entity.Result;
import com.example.web.entity.User;
import com.example.web.repository.UserRepository;
import com.example.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/getImg",method = RequestMethod.POST)
    public void getimg(String address, HttpServletResponse response) throws IOException {
        try {
            FileInputStream hFile=new FileInputStream(address);
            int i=hFile.available();
            byte data[]=new byte[i];
            hFile.read(data);
            hFile.close();
            response.setContentType("image/*");
            OutputStream toClient=response.getOutputStream();
            toClient.write(data);
            toClient.close();
        }catch (IOException e) {
            PrintWriter toClient = response.getWriter();
            response.setContentType("text/html;charset=gb2312");
            toClient.write("无法打开图片");
            toClient.close();
        }
    }
}
