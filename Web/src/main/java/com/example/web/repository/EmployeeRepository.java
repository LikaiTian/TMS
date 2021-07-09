package com.example.web.repository;

import com.example.web.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Memory
 * @Date 2021/7/9 17:09
 * @Version 1.0
 */
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Employee findById(Integer id);
    Employee findByIdNum(String idNum);
}
