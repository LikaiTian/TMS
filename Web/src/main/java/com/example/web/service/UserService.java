package com.example.web.service;

import com.example.web.enm.Message;
import com.example.web.entity.Result;
import com.example.web.entity.User;
import com.example.web.repository.UserRepository;
import com.example.web.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @Author Memory
 * @Date 2021/7/7 16:38
 * @Version 1.0
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    /**
     * hr注册
     * @param user
     * @return
     */
    public Result register(User user){
        return ResultUtils.success(userRepository.save(user));
    }

    /**
     * 更新hr信息
     * @param user
     * @return
     */
    public Result UpdateOne(User user){
        if(user.getId()==null){
            return ResultUtils.error(Message.NO_ID);
        }
        userRepository.delete(userRepository.findById(user.getId()));
        return ResultUtils.success(userRepository.save(user));
    }

    /**
     * 删除hr信息
     * @param id
     * @return
     */
    public Result deleteOne(Integer id){
        User user = userRepository.findById(id);
        if(user==null){
            return ResultUtils.error(Message.USER_NOT_EXIST);
        }
        userRepository.delete(user);
        return ResultUtils.success(userRepository.findAll());
    }

    /**
     * hr登录
     * @param phone
     * @param password
     * @return
     */
    public Result login(String phone,String password){

        User user=userRepository.findByPhoneAndPassword(phone,password);
        if(user==null){
            return ResultUtils.error(Message.USER_ERR_PASS);
        }
        return ResultUtils.success(user);
    }

}
