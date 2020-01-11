package com.example.sessiondemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.sessiondemo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByUsername(String username);

    User getByEmail(String email);
}
