package com.example.sessiondemo.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(name = "user_ext")
@Data
public class UserExt implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @NotBlank
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    @NotBlank
    private String description;

}
