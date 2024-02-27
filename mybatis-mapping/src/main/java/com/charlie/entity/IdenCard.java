package com.charlie.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IdenCard {
    /**
     * CREATE TABLE `idencard` (
     *  `id` INT PRIMARY KEY AUTO_INCREMENT,
     *  `card_sn` VARCHAR(32) NOT NULL DEFAULT ''
     * )CHARSET=utf8;
     */
    private Integer id;
    private String card_sn;
    private Person person;
}
