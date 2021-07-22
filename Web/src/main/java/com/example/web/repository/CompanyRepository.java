package com.example.web.repository;

import com.example.web.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Memory
 * @Date 2021/7/12 15:06
 * @Version 1.0
 */
@Repository("coJpaRepository")
public interface CompanyRepository extends JpaRepository<Company,Integer> {
    Company findByCompanyName(String companyName);
}
