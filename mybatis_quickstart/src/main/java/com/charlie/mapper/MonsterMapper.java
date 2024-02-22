package com.charlie.mapper;

import com.charlie.entity.Monster;

import java.util.List;

/*
1. 这是一个接口
2. 该接口用于声明操作monster表的方法
3. 这些方法可以通过注解或者xml文件实现
 */
public interface MonsterMapper {
    // 添加monster
    public void addMonster(Monster monster);

    // 根据id删除一个monster
    public void delMonster(Integer id);

    // 修改Monster
    public void updateMonster(Monster monster);

    // 查询-根据id
    public Monster getMonsterById(Integer id);

    // 查询所有的Monster
    public List<Monster> findAllMonsters();
}
