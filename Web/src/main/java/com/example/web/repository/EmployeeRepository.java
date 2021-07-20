package com.example.web.repository;

import com.example.web.entity.EmployInfo;
import com.example.web.entity.Employee;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * @Author Memory
 * @Date 2021/7/10 23:39
 * @Version 1.0
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findById(Integer id);
    Employee findByCardId(String cardId);
    Employee findByName(String name);
    List<Employee> findByCompanyAndDepartment(String company,String department);
    List<Employee> findByCompany(String company);
    Employee findByCompanyAndName(String company,String name);

    //Employee fin
}
