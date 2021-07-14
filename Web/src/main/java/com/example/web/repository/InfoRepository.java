package com.example.web.repository;

import com.example.web.entity.EmployInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author Memory
 * @Date 2021/7/12 10:06
 * @Version 1.0
 */
public interface InfoRepository extends JpaRepository<EmployInfo,Integer> {
    EmployInfo findById(Integer id);
    EmployInfo findByNameAndCompanyName(String name,String companyName);
    List<EmployInfo> findByName(String name);

}
