package com.example.web.service;

import com.example.web.enm.Message;
import com.example.web.entity.*;
import com.example.web.repository.EmployeeRepository;
import com.example.web.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
     * 公司员工信息的分页查询
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
        return getResult(page, pageSize, obj, matcher);
    }

    /**
     * 根据部门来查询员工信息
     * @param company
     * @param department
     * @param page
     * @param pageSize
     * @return
     */
    public Result findByDepartment(String company,String department,int page, int pageSize){
        Employee obj=new Employee();
        obj.setCompany(company);
        obj.setDepartment(department);
        //创建匹配器
        ExampleMatcher matcher =ExampleMatcher.matching()
                .withMatcher("company", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("department",ExampleMatcher.GenericPropertyMatchers.exact())
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
    private Result getResult(int page, int pageSize, Employee obj, ExampleMatcher matcher) {
        Example<Employee> ex=Example.of(obj,matcher);
        Pageable pageable = new PageRequest(page-1,pageSize);

        Page<Employee> kk= employeeRepository.findAll(ex,pageable);
        List<Employee> list= kk.getContent();
        return ResultUtils.success(list);
    }

    /**
     * 根据公司名和employee的名字查询
     * @param company
     * @param name
     * @return
     */
    public Result findByName(String company,String name,int page,int pageSize){
        Employee obj=new Employee();
        obj.setCompany(company);
        obj.setName(name);
        //创建匹配器
        ExampleMatcher matcher =ExampleMatcher.matching()
                .withMatcher("company", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("name",ExampleMatcher.GenericPropertyMatchers.exact())
                .withIgnorePaths("id");
        //创建实例
        return getResult(page, pageSize, obj, matcher);
    }

    /**
     * 返回该部门的各薪资段的人数以及该部门的平均薪资
     * @param company
     * @param department
     * @return
     */
    public Result salaryStatus(String company,String department){
        DecimalFormat dataFormat = new DecimalFormat( "0.00");
        List<Employee> employees= employeeRepository.findByCompanyAndDepartment(company, department);
        /*List<Double> salary=new ArrayList<Double>();*/
        double sumSalary=0;
        int[] salary=new int[4];
        for(int i=0;i<employees.size();i++){
            sumSalary+=employees.get(i).getSalary();
            if(employees.get(i).getSalary()<5000){
                salary[0]++;
                continue;
            }
            if(employees.get(i).getSalary()>=5000&&employees.get(i).getSalary()<10000){
                salary[1]++;
                continue;
            }
            if(employees.get(i).getSalary()>=10000&&employees.get(i).getSalary()<15000){
                salary[2]++;
                continue;
            }
            if(employees.get(i).getSalary()>=15000){
                salary[3]++;
                continue;
            }
        }
        double avg=sumSalary/(double)employees.size();
        double avgSalary=Double.parseDouble(dataFormat.format(avg));
        DepartmentSalary departmentSalary=new DepartmentSalary(salary,avgSalary);

        return ResultUtils.success(departmentSalary);
    }

    /**
     * 分析该公司的各部门平均薪资情况和人员分布情况
     * @param company
     * @return
     */
    public Result companySalary(String company){
        DecimalFormat dataFormat = new DecimalFormat( "0.00");
        List<Employee> employeeList=employeeRepository.findByCompany(company);
        int[] staff=new int[5];         //统计人员分布情况
        double[] salary=new double[5];  //每个部门的平均薪资

        for(int i=0;i<employeeList.size();i++){
            switch(employeeList.get(i).getDepartment()){
                case "项目部":
                    staff[0]++;
                    salary[0]+=employeeList.get(i).getSalary();
                    break;
                case "人事部":
                    staff[1]++;
                    salary[1]+=employeeList.get(i).getSalary();
                    break;

                case "财务部":
                    staff[2]++;
                    salary[2]+=employeeList.get(i).getSalary();
                    break;

                case"技术部":
                    staff[3]++;
                    salary[3]+=employeeList.get(i).getSalary();
                    break;

                case "销售部":
                    staff[4]++;
                    salary[4]+=employeeList.get(i).getSalary();

                    break;
            }
        }
        for(int i=0;i<5;i++){
            if(staff[i]==0){
                salary[i]=Double.parseDouble(dataFormat.format(salary[i]));
                continue;
            }
            salary[i]/=staff[i];
            salary[i]=Double.parseDouble(dataFormat.format(salary[i]));
        }
        CompanySalary companySalary=new CompanySalary();
        companySalary.setSalary(salary);
        companySalary.setEmployees(staff);
        return ResultUtils.success(companySalary);
    }
}

