package com.example.web.controller;


import com.example.web.entity.Employee;
import com.example.web.entity.Result;
import com.example.web.service.EmployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Memory
 * @Date 2021/7/11 14:13
 * @Version 1.0
 */
@RestController
@RequestMapping("/employ")
public class EmployeeController {
    @Autowired
    EmployService employService;

    /**
     * 新增一个员工
     * @param cardId
     * @param name
     * @param sex
     * @param birth
     * @param phone
     * @param company
     * @param department
     * @param job
     * @param salary
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public Result add(String cardId,String name,String sex,
                      String birth,String phone,String company,
                      String department,String job,String salary){
        return employService.addOne(cardId, name, sex, birth, phone, company, department, job, salary);
    }

    /**
     * 更新一个员工信息
     * @param employee
     * @return
     */
    @RequestMapping(value = "/modify",method = RequestMethod.GET)
    public Result update(Employee employee){
        return employService.updateOne(employee);
    }

    /**
     * 辞退一个员工
     * @param employee
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Result delete(Employee employee){
        return employService.deleteOne(employee);
    }

    /**
     * 根据公司查找员工
     * @param company
     * @return
     */
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public Result findAll(String company){
        return employService.findByCompany(company);
    }

    /**
     * 根据公司部门查找员工信息
     * @param company
     * @param department
     * @return
     */
    @RequestMapping(value = "/findByDepartment",method = RequestMethod.GET)
    public Result findByCompanyAndDepartment(String company,String department,int page,int pageSize){
        return employService.findByCompanyAndDepartment(company, department);
    }

    /**
     * 根据id来查询用户具体信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/findById",method = RequestMethod.GET)
    public  Result findById(Integer id){
        return employService.finById(id);
    }
    /*public String fileUpload(
            MultipartFile file) {
        try {
            String name = file.getOriginalFilename() == null ? "" : file.getOriginalFilename();
            String path = FileUtil.getTmpPath(Enums.TmpPath.UPLOAD.getCode()) + File.separator + name;
            File uploadFile = new File(path);
            file.transferTo(uploadFile);
            return "文件上传成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "文件上传失败";
        }
    }*/

    /**
     * 根据公司分页查询
     * @param company
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/pageQuery",method = RequestMethod.GET)
    public Result pageQuery(String company,int page, int pageSize){
        return employService.findAll(company, page, pageSize);
    }

    /**
     * 根据部门查询
     * @param company
     * @param department
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/departPageQuery",method = RequestMethod.GET)
    public Result findByDepartment(String company,String department,int page, int pageSize){
        return employService.findByDepartment(company, department, page, pageSize);
    }
    /**
     * 根据公司名和employee名字查询
     * @param company
     * @param name
     * @return
     */
    @RequestMapping(value = "/findByName",method = RequestMethod.GET)
    public Result findByName(String company,String name,int page,int pageSize){
        return employService.findByName(company, name,page,pageSize);
    }

    /**
     * 返回该部门的各薪资段的人数以及该部门的平均薪资
     * @param company
     * @param department
     * @return
     */
    @RequestMapping(value = "/salaryStatus",method = RequestMethod.GET)
    public Result salaryStatus(String company,String department){
        return employService.salaryStatus(company, department);
    }

    /**
     * 返回该部门的各薪资段的人数以及该部门的平均薪资
     * @param company
     * @return
     */
    @RequestMapping(value = "/companySalary",method = RequestMethod.GET)
    public Result companySalary(String company){
        return employService.companySalary(company);
    }
}