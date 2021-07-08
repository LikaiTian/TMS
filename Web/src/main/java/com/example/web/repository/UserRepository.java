package com.example.web.repository;

import com.example.web.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @Author Memory
 * @Date 2021/7/7 16:31
 * @Version 1.0
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    User findById(Integer id);
    User findByPhone(String phone);
    User findByPhoneAndPassword(String phone,String password);
}
