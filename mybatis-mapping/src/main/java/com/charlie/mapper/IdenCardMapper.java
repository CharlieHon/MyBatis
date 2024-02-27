package com.charlie.mapper;

import com.charlie.entity.IdenCard;

public interface IdenCardMapper {
    // 根据id获取身份证序列号
    public IdenCard getIdenCardById(Integer id);

    // 通过id查询身份证信息，并返回其对应的person信息
    public IdenCard getIdenCardById2(Integer id);
}
