package com.charlie.mapper;

import com.charlie.entity.Monster;
import com.charlie.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonsterMapperTest {
    private SqlSession sqlSession;
    private MonsterMapper monsterMapper;

    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);
    }

    @Test
    public void findMonsterByNameORId() {
        Monster monster = new Monster();
        monster.setId(3);
        monster.setName("青牛怪");
        List<Monster> monsters = monsterMapper.findMonsterByNameORId(monster);
        for (Monster monster1 : monsters) {
            System.out.println("monster=" + monster1);
        }

        if (sqlSession != null) {
            //sqlSession.commit();    // 查，可以省略
            sqlSession.close();
        }

        System.out.println("OK!");
    }

    @Test
    public void findMonsterByName() {
        List<Monster> monsters = monsterMapper.findMonsterByName("怪");
        for (Monster monster1 : monsters) {
            System.out.println("monster=" + monster1);
        }

        if (sqlSession != null) {
            //sqlSession.commit();    // 查，可以省略
            sqlSession.close();
        }

        System.out.println("OK!");
    }

    @Test
    public void findMonsterByIdAndSalary_ParameterHashMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 4);
        map.put("salary", 1000);
        List<Monster> monsters = monsterMapper.findMonsterByIdAndSalary_ParameterHashMap(map);
        for (Monster monster : monsters) {
            System.out.println("monster=" + monster);
        }
        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("OK!");
    }

    @Test
    public void findMonsterByIdAndSalary_ParameterHspMap_ReturnHashMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 4);
        map.put("salary", 1000);
        List<Map<String, Object>> monsters = monsterMapper.findMonsterByIdAndSalary_ParameterHspMap_ReturnHashMap(map);

        // 以Map形式取出返回的结果
        for (Map<String, Object> monsterMap : monsters) {
            // monsterMap={birthday=2024-02-24, gender=0, name=黄风怪, id=5, salary=1010.0, age=10, email=kiki@qq.com}
            //System.out.println("monsterMap=" + monsterMap);

            // 遍历monsterMap，取出属性和对应值
            // 方式一：keySet()
            //Set<String> keys = monsterMap.keySet();
            //for (String key : keys) {
            //    System.out.println(key + "=>" + monsterMap.get(key));
            //}

            // 方式二：entrySet()
            for (Map.Entry<String, Object> entry : monsterMap.entrySet()) {
                System.out.println(entry.getKey() + "=>" + entry.getValue());
            }

            System.out.println("********");
        }

        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("OK!");
    }
}
