package com.example.sessiondemo.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    private long id;
    private String username;
    private String password;
    private String email;
    private String role;

    public User(String username, String password, String email, String role){
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId(){
        return id;
    };

    public void setId(long id){
        this.id = id;
    }

    @Column(name="username", unique = true, nullable = false)
    public String getUsername(){
        return username;
    };

    public void setUsername(String username){
        this.username = username;
    }

    @Column(name="password", nullable = false)
    public String getPassword(){
        return password;
    };
    public void setPassword( String password){
        this.password = password;
    }


    @Column(name="email", nullable = false)
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    @Column(name="role", nullable = false)
    public String getRole(String role){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }

//    public void setId(String toString) {
//        this.userId = toString;
//    }

//    private Timestamp registerTime = new Timestamp(new Date().getTime());
//    private Boolean activated = Boolean.FALSE;
//    private Integer lockStatus = 0;
//    private String role = "USER";

      public User(){
      }

//    public User(String username, String email, String password, String role){
//        this.username = username;
//        this.email = email;
//        this.password = password;
////        this.registerTime = registerTime;
////        this.activated = activated;
////        this.lockStatus = lockStatus;
//        this.role = role;
//    }

}
