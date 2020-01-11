package com.example.sessiondemo.repository;

import com.example.sessiondemo.entity.UserExt;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface UserExtRepository extends JpaRepository<UserExt, String> {

}
