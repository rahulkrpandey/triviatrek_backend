package com.rahul.TriviaTrek.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="user_table")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Boolean admin;
}
