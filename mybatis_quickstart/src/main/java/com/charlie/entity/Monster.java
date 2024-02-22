package com.charlie.entity;

import java.util.Date;

/* Monster对象 和 monster表对应，体现OOP
1. 这是一个普通的POJO类
2. 使用原生的sql语句查询结果还是要封装成对象
3. 要求这里的实体类属性名和表名字段保持一致
 */
public class Monster {
    /*
    CREATE TABLE monster (
        `id` INT NOT NULL AUTO_INCREMENT,
        `age` INT NOT NULL,
        `birthday` DATE DEFAULT NULL,
        `email` VARCHAR(255) NOT NULL,
        `gender` TINYINT NOT NULL,
        `name` VARCHAR(255) NOT NULL,
        `salary` DOUBLE NOT NULL,
        PRIMARY KEY(`id`)
    )CHARSET=utf8;
     */
    // 属性-和表的字段有对应关系
    private Integer id;
    private Integer age;
    private String name;
    private String email;
    private Date birthday;
    private Double salary;
    private Integer gender;

    public Monster() {}

    public Monster(Integer id, Integer age, String name, String email, Date birthday, Double salary, Integer gender) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.salary = salary;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", salary=" + salary +
                ", gender=" + gender +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
