package com.example.web.service;

import com.example.web.enm.Message;
import com.example.web.entity.Employee;
import com.example.web.entity.Result;
import com.example.web.entity.User;
import com.example.web.repository.EmployeeRepository;
import com.example.web.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Memory
 * @Date 2021/7/10 23:43
 * @Version 1.0
 */
@Service
public class EmployService {

    //判断电话号码是否合法的正则表达式
    public final String REG_EXP = "^([0-9]{3}-?[0-9]{8}|[0-9]{4}-?[0-9]{7})$";
    @Autowired
    EmployeeRepository employeeRepository;

    /**
     *添加员工后返回该员工所在公司的员工列表
     * @param employee
     * @return
     */
    public Result addOne(Employee employee){
        Employee employee1=employeeRepository.findByName(employee.getName());
        if(employee1!=null){
            return ResultUtils.error(Message.EMPLOY_IS_EXIST);
        }
        //判断电话的合法性
        if(!employee.getPhone().matches(REG_EXP)){
            return ResultUtils.error(Message.PHONE_NOT_LEGAL);
        }
        //判断卡号是否合法
        if(!employee.getCardId().matches("^\\d{19}$")){
            return ResultUtils.error(Message.CARDID_NOT_LEGAL);
        }
        employeeRepository.save(employee);
        return ResultUtils.success("员工添加成功！");
    }

    /**
     * 更新一个员工信息，更新完以后返回该公司员工列表
     * @param employee
     * @return
     */
    public Result updateOne(Employee employee){
        //判断是否传过了ID
        if(employee.getId()==null){
            return ResultUtils.error(Message.NO_ID);
        }
        //判断员工是否存在
        Employee employee0 = employeeRepository.findById(employee.getId());
        if(employee0==null){
            return ResultUtils.error(Message.USER_NOT_EXIST);
        }
        //判断电话的合法性
        if(!employee.getPhone().matches(REG_EXP)){
            return ResultUtils.error(Message.PHONE_NOT_LEGAL);
        }
        //判断卡号是否合法
        if(!employee.getCardId().matches("^\\d{19}$")){
            return ResultUtils.error(Message.CARDID_NOT_LEGAL);
        }
        //成功则保存,返回成功
        employeeRepository.save(employee);
        return ResultUtils.success("员工修改成功！");
    }

    /**
     * 删除一个员工后，返回该公司员工列表
     * @param employee
     * @return
     */
    public Result deleteOne(Employee employee){
        if(employee.getId()==null){
            return ResultUtils.error(Message.NO_ID);
        }
        Employee employee0 = employeeRepository.findById(employee.getId());
        if(employee0==null){
            return ResultUtils.error(Message.USER_NOT_EXIST);
        }
        employeeRepository.delete(employee);
        return ResultUtils.success("员工删除成功！");
    }
    /**
     * 根据公司找员工信息列表
     * @param company
     * @return
     */
    public Result findByCompany(String company){
        return ResultUtils.success(employeeRepository.findByCompany(company));
    }

    /**
     * 根据公司和部门查找员工信息列表
     * @param company
     * @param department
     * @return
     */
    public Result findByCompanyAndDepartment(String company,String department){
        return ResultUtils.success(employeeRepository.findByCompanyAndDepartment(company, department));
    }

    /**
     * 根据员工id查询它的信息
     * @param id
     * @return
     */
    public Result finById(Integer id){
        return ResultUtils.success(employeeRepository.findById(id));
    }

    /**
     * 分页查询
     * @param company
     * @param page
     * @param pageSize
     * @return
     */
    public Result findAll(String company,int page, int pageSize) {
        Employee obj=new Employee();
        obj.setCompany(company);
        //创建匹配器
        ExampleMatcher matcher =ExampleMatcher.matching()
                .withMatcher("company", ExampleMatcher.GenericPropertyMatchers.exact())
                .withIgnorePaths("id");
        //创建实例
        Example<Employee> ex=Example.of(obj,matcher);
        Pageable pageable = new PageRequest(page-1,pageSize);

        Page<Employee> kk= employeeRepository.findAll(ex,pageable);
        List<Employee> list= kk.getContent();
        return ResultUtils.success(list);
    }


}
