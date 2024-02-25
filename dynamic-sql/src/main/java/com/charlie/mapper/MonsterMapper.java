package com.charlie.mapper;


import com.charlie.entity.Monster;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MonsterMapper {
    // 根据age查询结果,测试if
    public List<Monster> findMonsterByAge(@Param(value = "age") Integer age);

    // 根据id和age查询结果.测试where
    public List<Monster> findMonsterByIdAndName(Monster monster);

    // 测试choose标签的使用
    public List<Monster> findMonsterByIdOrName_choose(Map<String, Object> map);

    // 测试foreach标签
    public List<Monster> findMonsterById_forEach(Map<String, Object> m);

    // 测试 trim
    public List<Monster> findMonsterByName_trim(Map<String, Object> map);

    // 测试 set
    public void updateMonster_set(Map<String, Object> map);
}
