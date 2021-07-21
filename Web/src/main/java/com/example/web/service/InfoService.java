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
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.xml.ws.Action;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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
     * 本公司
     * @param company
     * @param page
     * @param pageSize
     * @return
     */
    public Result myCompany(String company,int page, int pageSize){
        EmployInfo obj=new EmployInfo();
        obj.setCompanyName(company);
        ExampleMatcher matcher =ExampleMatcher.matching()
                .withMatcher("companyName", ExampleMatcher.GenericPropertyMatchers.exact())
                .withIgnorePaths("id");
        //创建实例
        return getResult(page, pageSize, obj, matcher);
    }

    /**
     * 查询处于离职状态的某员工在以往公司的评价信息
     * @param name
     * @param page
     * @param pageSize
     * @return
     */
    public Result query(String name,int page, int pageSize){
        EmployInfo obj=new EmployInfo();
        obj.setName(name);
        ExampleMatcher matcher =ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.exact())
                .withIgnorePaths("id");
        //创建实例
        return getResult(page, pageSize, obj, matcher);
    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param obj
     * @param matcher
     * @return
     */
    @NotNull
    private Result getResult(int page, int pageSize, EmployInfo obj, ExampleMatcher matcher) {
        Example<EmployInfo> ex=Example.of(obj,matcher);
        Pageable pageable = new PageRequest(page-1,pageSize);

        Page<EmployInfo> kk= infoRepository.findAll(ex,pageable);
        List<EmployInfo> list= kk.getContent();
        return ResultUtils.success(list);
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
            return ResultUtils.success("http://1.15.24.31:8080/pictures/"+randomUUID+file.getOriginalFilename());
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
