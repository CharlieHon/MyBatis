package com.charlie.mapper;

import com.charlie.entity.Person;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface PersonMapperAnnotation {
    // 注解实现级联查询，注解形式就是对xml配置方式的改写
    @Select("select * from `person` where `id`=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "card", column = "card_id",
                    one = @One(select = "com.charlie.mapper.IdenCardMapper.getIdenCardById"))
    })
    public Person getPersonById(Integer id);
}
