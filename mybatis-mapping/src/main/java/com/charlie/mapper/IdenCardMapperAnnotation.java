package com.charlie.mapper;

import com.charlie.entity.IdenCard;
import org.apache.ibatis.annotations.Select;

// 使用注解方式实现一对一映射
public interface IdenCardMapperAnnotation {
    @Select("select * from `idencard` where `id`=#{id}")
    public IdenCard getIdenCardById(Integer id);
}
