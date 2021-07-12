package com.example.web.repository;

import com.example.web.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Memory
 * @Date 2021/7/12 15:06
 * @Version 1.0
 */
public interface CompanyRepository extends JpaRepository<Company,Integer> {
    Company findByCompanyName(String companyName);
}
