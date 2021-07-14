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
        employeeRepository.save(employee);
        return ResultUtils.success("员工添加成功！");
    }

    /**
     * 更新一个员工信息，更新完以后返回该公司员工列表,这些员工是这个公司的并且状态为1
     * @param employee
     * @return
     */
    public Result updateOne(Employee employee){
        if(employee.getId()==null){
            return ResultUtils.error(Message.NO_ID);
        }
        Employee employee0 = employeeRepository.findById(employee.getId());
        if(employee0==null){
            return ResultUtils.error(Message.USER_NOT_EXIST);
        }
        employeeRepository.save(employee);
        return ResultUtils.success("员工修改成功！");
    }

    /**
     * 删除一个员工后，返回该公司员工列表,这些员工是这个公司的并且状态为1
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