package com.example.web.service;

import com.example.web.enm.Message;
import com.example.web.entity.EmployInfo;
import com.example.web.entity.Employee;
import com.example.web.entity.Result;
import com.example.web.repository.EmployeeRepository;
import com.example.web.repository.InfoRepository;
import com.example.web.utils.ResultUtils;
import com.mysql.jdbc.util.ResultSetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Action;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @Author Memory
 * @Date 2021/7/12 10:57
 * @Version 1.0
 */
@Service
public class InfoService {

    @Autowired
    InfoRepository infoRepository;
    EmployeeRepository employeeRepository;

    /**
     * 添加员工评价
     * @param employInfo
     * @return
     */
    public Result add(EmployInfo employInfo){
        return ResultUtils.success(infoRepository.save(employInfo));
    }

    /**
     * 查询处于离职状态的某员工在以往公司的评价信息
     * @param name
     * @return
     */
    public Result query(String name){
        Employee employee=employeeRepository.findByName(name);
        if(employee!=null){
            return ResultUtils.error(Message.EMPLOY_IN_JOB);
        }
        return ResultUtils.success(infoRepository.findByName(name));
    }

    /**
     * 员工评价上传附件
     * @param file
     * @return
     */
    public Result fileUpload(
            MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            //生成不一样的名字
            UUID randomUUID = UUID.randomUUID();
            Path path = Paths.get("/data/"+randomUUID+file.getOriginalFilename());
            Files.write(path,bytes);
            return ResultUtils.success(path);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultUtils.error(Message.FILE_UPLOAD_ERROR);
        }
    }

    public Result downLoad(String fileName, HttpServletResponse response){
        if(fileName==null){
            return ResultUtils.error(Message.FILE_DOWNLOAD_ERROR);
        }
        String path = "/data/";  //这里指定路径在C盘根目录，按需改动即可
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            File file = new File(path, fileName);
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        }catch(FileNotFoundException e) {
            e.printStackTrace();
            return ResultUtils.error(Message.FILE_DOWNLOAD_ERROR);

        } catch (IOException e) {
            e.printStackTrace();
            return ResultUtils.error(Message.FILE_DOWNLOAD_ERROR);

        } finally{
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return ResultUtils.success("文件下载成功！");
        }
    }
}
