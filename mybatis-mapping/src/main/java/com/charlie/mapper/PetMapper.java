package com.charlie.mapper;

import com.charlie.entity.Pet;

import java.util.List;

public interface PetMapper {
    // 通过User的id来获取pet对象，可能有多个，因此使用List接收
    public List<Pet> getPetByUserId(Integer userId);
    // 通过pet的id返回Pet对象
    public Pet getPetById(Integer id);
}
