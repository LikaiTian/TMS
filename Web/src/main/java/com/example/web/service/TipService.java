package com.example.web.service;

import com.example.web.entity.Result;
import com.example.web.entity.Tip;
import com.example.web.repository.TipRepository;
import com.example.web.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Memory
 * @Date 2021/7/20 18:40
 * @Version 1.0
 */
@Service
public class TipService {
    @Autowired
    TipRepository tipRepository;

    /**
     * 添加操作信息
     * @param userId
     * @param message
     * @param time
     * @return
     */
    public Result add(String userId,String message,String time){
        SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd HH-mm-ss" );

        Tip tip=new Tip();
        tip.setTime(time);    //设置时间
        tip.setMessage(message);                //设置消息
        tip.setUserId(Integer.valueOf(userId)); //设置用户id
        tipRepository.save(tip);
        return ResultUtils.success("消息保存成功啦！");
    }

    /**
     * 根据用户id返回所有操作信息
     * @param userId
     * @return
     */
    public Result query(String userId){
        int userid=Integer.valueOf(userId);
        return ResultUtils.success(tipRepository.findByUserId(userid));
    }
}
