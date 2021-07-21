package com.example.web.service;

import com.example.web.enm.Message;
import com.example.web.entity.Result;
import com.example.web.entity.User;
import com.example.web.repository.UserRepository;
import com.example.web.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author Memory
 * @Date 2021/7/7 16:38
 * @Version 1.0
 */
@Service
public class UserService {

    //判断电话号码是否合法的正则表达式
    public final String REG_EXP = "^([0-9]{3}-?[0-9]{8}|[0-9]{4}-?[0-9]{7})$";
    //判断邮箱是否合法
    public final String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    @Autowired
    UserRepository userRepository;

    /**
     * 注册
     * @param user
     * @return
     */
    public Result register( User user){
        User user0=userRepository.findByPhone(user.getPhone());
        if(user0!=null){
            return ResultUtils.error(Message.USER_IS_EXIST);
        }

        //判断电话的合法性
        if(!user.getPhone().matches(REG_EXP)){
            return ResultUtils.error(Message.PHONE_NOT_LEGAL);
        }

        //判断邮箱是否合法
        Pattern t;
        Matcher q;
        t = Pattern.compile(regEx1);
        q = t.matcher(user.getEmail());
        if (!q.matches()){
            return ResultUtils.error(Message.EMAIL_NOT_LEGAL);
        }

        //判断密码合法性
        String password=user.getPassword();
        if (password.length() > 0) {

            //判断是否有空格字符串
            for (int ww = 0; ww < password.length(); ww++) {
                String b = password.substring(ww, ww + 1);
                if (b.equals(" ")) {
                    return ResultUtils.error(Message.CHARACTER_EMPTY);
                }
            }

            //判断是否有汉字
            int count = 0;
            String regEx = "[\\u4e00-\\u9fa5]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(password);
            while (m.find()) {
                for (int i = 0; i <= m.groupCount(); i++) {
                    count = count + 1;
                }
            }
            /*if(count>0){
                return ResultUtils.error(Message.HAS_CHINESE);
            }
            String t1 = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$\n";

            //判断密码是否为数字和字母
            if(!password.matches((t1))){
                return ResultUtils.error(Message.PASSWORD_STYLE);
            }*/

        }

        user.setAddress("http://1.15.24.31:8080/pictures/03d318ca-891d-4c0f-b649-0d908f35de975ed503a7d75b10e2.jpg");
        //成功则返回成功信息
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
        User user0 = userRepository.findById(user.getId());
        if(user0==null){
            return ResultUtils.error(Message.USER_NOT_EXIST);
        }
        return ResultUtils.success(userRepository.save(user));
    }

    /**
     * 删除hr信息
     * @param user
     * @return
     */
    public Result deleteOne(User user){
        if(user.getId()==null){
            return ResultUtils.error(Message.NO_ID);
        }
        User user0 = userRepository.findById(user.getId());
        if(user0==null){
            return ResultUtils.error(Message.USER_NOT_EXIST);
        }
        userRepository.delete(user);
        return ResultUtils.success();
    }

    /**
     * 登录
     * @param request
     * @param user
     * @return
     */
    public Result login(HttpServletRequest request, User user){
        User user0=userRepository.findByPhoneAndPassword(user.getPhone(),user.getPassword());
        //查不到表示未注册
        if(user0==null){return ResultUtils.error(Message.USER_ERR_PASS);
        }
        HttpSession session=request.getSession();
        session.setAttribute("user",user0);
        return ResultUtils.success(user0);
    }

    /**
     * 根据id查找个人信息
     * @param id
     * @return
     */
    public User findOne(Integer id){
        User user0 = userRepository.findById(id);
        if(user0==null){
            return null;
        }
        return user0;
    }

    /**
     * 修改头像
     * @param id
     * @param file
     * @return
     */
    public Result fileUpload(Integer id,MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            //生成不一样的名字
            UUID randomUUID = UUID.randomUUID();
            Path path = Paths.get("/data/"+randomUUID+file.getOriginalFilename());
            Files.write(path,bytes);

            User user=userRepository.findById(id);
            user.setAddress("http://1.15.24.31:8080/pictures/"+randomUUID+file.getOriginalFilename());
            userRepository.save(user);
            return ResultUtils.success("http://1.15.24.31:8080/pictures/"+randomUUID+file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            return ResultUtils.error(Message.IMG_ERROR);
        }
    }

    /**
     * 根据电话返回hr信息
     * @param phone
     * @return
     */
    public Result findByPhone(String phone){
        return ResultUtils.success(userRepository.findByPhone(phone));
    }

    /**
     * 退出
     * @return
     */
    public Result exit(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return ResultUtils.success(Message.EXIT_SUCCESS);
    }


    public Result findAddress(int id){
        User user=userRepository.findById(id);
        return ResultUtils.success(user.getAddress());
    }
}
