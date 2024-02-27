package com.charlie.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Person {
    /**
     * CREATE TABLE `person` (
     *  `id` INT PRIMARY KEY AUTO_INCREMENT,
     *  `name` VARCHAR(32) NOT NULL DEFAULT '',
     *  `card_id` INT,	-- 对应idencard的主键id
     *  FOREIGN KEY (`card_id`) REFERENCES `idencard`(`id`) -- 外键
     * )CHARSET=utf8;
     */
    private Integer id;
    private String name;
    // 因为我们要实现一个级联操作，一个人需要对应一个身份证
    // 所以这里需要直接定义IdenCard，属性名自定义
    // X private Integer card_id; X
    private IdenCard card;
}
