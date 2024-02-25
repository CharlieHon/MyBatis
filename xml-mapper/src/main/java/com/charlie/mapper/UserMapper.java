package com.charlie.mapper;

import com.charlie.entity.User;

import java.util.List;

public interface UserMapper {
    // 添加用户
    public void addUser(User user);
    // 查询所有的User
    public List<User> findAllUser();
}
