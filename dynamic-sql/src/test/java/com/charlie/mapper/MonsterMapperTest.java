package com.charlie.mapper;

import com.charlie.entity.Monster;
import com.charlie.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class MonsterMapperTest {
    private SqlSession sqlSession;
    private MonsterMapper monsterMapper;

    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);
        System.out.println("monsterMapper=" + monsterMapper.getClass());
    }

    // 测试 if 标签
    @Test
    public void findMonsterByAge() {
        List<Monster> monsters = monsterMapper.findMonsterByAge(-1);
        for (Monster monster : monsters) {
            System.out.println("monster=" + monster);
        }

        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("OK!");
    }

    // 测试 where 标签
    @Test
    public void findMonsterByIdAndName() {
        Monster monster = new Monster();
        monster.setId(4);
        monster.setName("");
        List<Monster> monsters = monsterMapper.findMonsterByIdAndName(monster);
        for (Monster monster1 : monsters) {
            System.out.println("monster=" + monster1);
        }

        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("OK!");
    }

    // 测试 choose-when-otherwise 标签
    @Test
    public void findMonsterByIdOrName_choose() {
        Map<String, Object> map = new HashMap<>();
        //map.put("name", "青牛怪");
        map.put("id", -1);
        List<Monster> monsters = monsterMapper.findMonsterByIdOrName_choose(map);
        for (Monster monster : monsters) {
            System.out.println("monster=" + monster);
        }

        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("OK!");
    }

    // 测试 foreach
    @Test
    public void findMonsterById_forEach() {
        Map<String, Object> map = new HashMap<>();
        map.put("ids", Arrays.asList(4, 5, 6));
        List<Monster> monsters = monsterMapper.findMonsterById_forEach(map);
        for (Monster monster : monsters) {
            System.out.println("monster=" + monster);
        }

        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("OK!");
    }

    // 测试 trim
    @Test
    public void findMonsterByName_trim() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "青牛怪");
        map.put("age", 20);
        List<Monster> monsters = monsterMapper.findMonsterByName_trim(map);
        for (Monster monster : monsters) {
            System.out.println("monster=" + monster);
        }

        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("OK!");
    }

    // 测试 set标签
    @Test
    public void updateMonster_set() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 8);
        map.put("name", "老鼠精");
        map.put("age", 125);
        monsterMapper.updateMonster_set(map);

        // 修改需要commit
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("OK!");
    }
}
