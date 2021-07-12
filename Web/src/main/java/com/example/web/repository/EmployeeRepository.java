package com.example.web.repository;

import com.example.web.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

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
    List<Employee> findByCompany(String company);
    List<Employee> findByCompanyAndDepartment(String company,String department);
    //Employee fin
}
