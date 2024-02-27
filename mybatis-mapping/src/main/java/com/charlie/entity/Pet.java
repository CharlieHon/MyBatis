package com.charlie.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
//@ToString
public class Pet {
    /**
     * -- 创建mybatis_pet表
     * CREATE TABLE `mybatis_pet` (
     *  `id` INT PRIMARY KEY AUTO_INCREMENT,
     *  `nickname` VARCHAR(32) NOT NULL DEFAULT '',
     *  `user_id` INT,
     *  FOREIGN KEY (`user_id`) REFERENCES `mybatis_user`(`id`)
     * )CHARSET=utf8;
     */
    private Integer id;
    private String nickname;
    private User user;  // 一个pet对应的一个user对象

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
