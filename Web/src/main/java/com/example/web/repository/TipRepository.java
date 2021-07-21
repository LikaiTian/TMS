package com.example.web.repository;

import com.example.web.entity.Tip;
import com.example.web.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author Memory
 * @Date 2021/7/20 18:38
 * @Version 1.0
 */
public interface TipRepository extends JpaRepository<Tip,Integer> {
    List<Tip> findByUserId(Integer userId);
}
