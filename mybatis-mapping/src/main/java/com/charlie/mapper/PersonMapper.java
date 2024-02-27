package com.charlie.mapper;

import com.charlie.entity.Person;

public interface PersonMapper {
    // 通过id查询person，包含这个person关联的IndeCard对象
    public Person getPersonById(Integer id);

    // 方式2
    public Person getPersonById2(Integer id);

    // 根据card_id查询person信息
    public Person getPersonByCardId(Integer card_id);
}
