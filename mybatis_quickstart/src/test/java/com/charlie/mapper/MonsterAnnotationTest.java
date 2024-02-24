package com.charlie.mapper;

import com.charlie.entity.Monster;
import com.charlie.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class MonsterAnnotationTest {

    private SqlSession sqlSession;
    private MonsterAnnotation monsterAnnotation;

    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
        monsterAnnotation = sqlSession.getMapper(MonsterAnnotation.class);
        // 返回的依然是一个接口的代理对象：class com.sun.proxy.$Proxy11
        System.out.println("monsterAnnotation=" + monsterAnnotation.getClass());
    }

    @Test
    public void addMonster() {
        Monster monster = new Monster();
        monster.setAge(120);
        monster.setBirthday(new Date());
        monster.setEmail("kiki@qq.com");
        monster.setGender(1);
        monster.setName("金角大王");
        monster.setSalary(1020.0);
        // 使用在接口方法中注解的方式完成配置
        monsterAnnotation.addMonster(monster);

        // 添加后monster的id
        System.out.println("添加后monster:id=" + monster.getId());

        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("OK!");
    }

    @Test
    public void findAllMonsters() {
        // 使用接口配置注解的方式操作DB
        List<Monster> monsters = monsterAnnotation.findAllMonsters();
        for (Monster monster : monsters) {
            System.out.println("monster=" + monster);
        }
        System.out.println("OK!");
    }

    @Test
    public void deleteMonster() {
        monsterAnnotation.delMonster(9);
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("删除成功!");
    }
}
