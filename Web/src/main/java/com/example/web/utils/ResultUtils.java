package com.example.web.utils;

import com.example.web.enm.Message;
import com.example.web.entity.Result;

/**
 * @Author Memory
 * @Date 2021/7/8 21:26
 * @Version 1.0
 */
public class ResultUtils {

    /**
     * 带参code和数的成功
     * @param msg
     * @param date
     * @return
     */
    public static Result success(Message msg,Object date){
        Result result=new Result();
        result.setCode(msg.getCode());
        result.setMessage(msg.getMessage());
        result.setDate((date));
        return result;
    }

    /**
     * 带参数的成功
     * @param date
     * @return
     */
    public static Result success(Object date){
        Result result=new Result();
        result.setCode(0);
        result.setMessage("成功!");
        result.setDate((date));
        return result;
    }

    /**
     * 不带参数的成功
     * @return
     */
    public static Result success(){
        return success(null);
    }

    /**
     * 失败
     * @param msg
     * @return
     */
    public static Result error(Message msg){
        Result result = new Result();
        result.setCode(msg.getCode());
        result.setMessage(msg.getMessage());
        return result;
    }
}
