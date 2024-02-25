package com.charlie.mapper;

import com.charlie.entity.Monster;

import java.util.List;
import java.util.Map;

/*
1. 这是一个接口
2. 该接口用于声明操作monster表的方法
3. 这些方法可以通过注解或者xml文件实现
 */
public interface MonsterMapper {
    // 通过id或者名字进行查询
    public List<Monster> findMonsterByNameORId(Monster monster);

    // 查询名字中含有 "精" 的妖怪
    public List<Monster> findMonsterByName(String name);

    // 查询 id > 3 并且 salary > 3000 的妖怪，要求使用HashMap形式参数
    public List<Monster> findMonsterByIdAndSalary_ParameterHashMap(Map<String, Object> map);

    // 查询 id > 3 并且 salary > 3000 的妖怪，要求传入参数和返回类型都是 HashMap
    public List<Map<String, Object>> findMonsterByIdAndSalary_ParameterHspMap_ReturnHashMap(Map<String, Object> map);
}
