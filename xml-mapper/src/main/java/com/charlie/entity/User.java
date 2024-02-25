package com.charlie.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class User {
    private Integer user_id;
    private String username;
    private String useremail;
}
