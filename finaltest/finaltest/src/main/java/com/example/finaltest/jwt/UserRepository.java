package com.example.finaltest.jwt;

import com.example.finaltest.jwt.model.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDao, Integer> {
    UserDao findByUsername(String name);
}
