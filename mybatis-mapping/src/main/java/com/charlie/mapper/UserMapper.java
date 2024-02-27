package com.charlie.mapper;

import com.charlie.entity.User;

public interface UserMapper {
    // 根据id返回User对象
    public User getUserById(Integer id);
}
