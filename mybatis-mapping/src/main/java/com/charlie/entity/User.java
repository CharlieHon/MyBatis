package com.charlie.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
//@ToString   // 会造成栈溢出
public class User {
    /**
     * CREATE TABLE `mybatis_user` (
     *  `id` INT PRIMARY KEY AUTO_INCREMENT,
     *  `name` VARCHAR(32) NOT NULL DEFAULT ''
     * )CHARSET=utf8;
     */
    private Integer id;
    private String name;
    // 因为一个User对象可以养多条宠物，mybatis使用集合体现这个关系
    private List<Pet> pets;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
