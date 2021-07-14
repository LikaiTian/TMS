package com.example.web.controller;

import com.example.web.enm.Message;
import com.example.web.entity.EmployInfo;
import com.example.web.entity.Result;
import com.example.web.service.InfoService;
import com.example.web.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * @Author Memory
 * @Date 2021/7/12 16:52
 * @Version 1.0
 */
@RestController
@RequestMapping("/comment")
public class InfoController {
    @Autowired
    InfoService infoService;

    /**
     * 添加员工评价
     * @param employInfo
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public Result add(EmployInfo employInfo){
        return infoService.add(employInfo);
    }

    /**
     * 查询处于离职状态的某员工在以往公司的评价信息
     * @param name
     * @return
     */
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public Result query(String name){
        return infoService.query(name);
    }

    /*@RequestMapping(value = "/upload",method = RequestMethod.POST)*/
    /*public Result upload(MultipartFile file){
        if(file!=null){
            String fileName=file.getOriginalFilename();

            String code=UUID.randomUUID().toString().replaceAll("-","");
            String newFileName=code+fileName.substring((fileName.lastIndexOf('.')));
            String currentDateFolder=(new SimpleDateFormat("/yyyy/MM/dd/")).format(new Date());

            String destFilePath="C:/Users/memory/Desktop/DotTraining/"+ File.separator + currentDateFolder + File.separator + newFileName;
            try{
                File destFile=new File(destFilePath);
                if (!destFile.exists()) {
                    destFile.mkdirs();
                }
                file.transferTo(destFile);
                return ResultUtils.success(destFilePath);
            }
            catch (IOException e){

            }
        }
        return ResultUtils.error(Message.FILE_UPLOAD_ERROR);
    }*/

    /**
     * 上传文件
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Result fileUpload(
            MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("/data/"+file.getOriginalFilename());
            Files.write(path,bytes);
            return ResultUtils.success("文件上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            return ResultUtils.error(Message.FILE_UPLOAD_ERROR);
        }
    }
}
