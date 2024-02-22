package com.charlie.mapper;

import com.charlie.entity.Monster;
import com.charlie.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class MonsterMapperTest {
    // 属性
    private SqlSession sqlSession;
    private MonsterMapper monsterMapper;

    // 编写方法，完成初始化
    @Before // 当方法标注了 @Before注解后，表示在执行目标测试方法前，会先执行该方法
    public void init() {
        System.out.println("init()...");
        // 获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        // 获取到MonsterMapper对象，底层使用了动态代理机制
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);
        // monsterMapper=class com.sun.proxy.$Proxy7 代理对象
        System.out.println("monsterMapper=" + monsterMapper.getClass());
    }

    @Test
    public void addMonster() {
        for (int i = 0; i < 2; i++) {
            Monster monster = new Monster();
            monster.setAge(10 + i);
            monster.setBirthday(new Date());
            monster.setEmail("kiki@qq.com");
            monster.setGender(i);
            monster.setName("黄风怪" + i);
            monster.setSalary(1000 + i * 10.0);
            monsterMapper.addMonster(monster);
            System.out.println("提示信息：添加对象-" + monster);
            System.out.println("添加到表中后，自增长id=" + monster.getId());
        }
        // 如果是**增删改**，需要提交事务
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("保存成功!");
    }

    @Test
    public void delMonster() {
        monsterMapper.delMonster(2);
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("删除成功!");
    }

    @Test
    public void updateMonster() {
        Monster monster = new Monster(2, 35, "九头虫", "cyer@qq.com", null, 45000., 1);
        monsterMapper.updateMonster(monster);
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("修改成功!");
    }

    @Test
    public void getMonsterById() {
        Monster monster = monsterMapper.getMonsterById(5);
        System.out.println("查询结果：" + monster);
        // 因为是select语句，所以不需要提交，但是仍然需要关闭连接
        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("查询成功!");
    }

    @Test
    public void findAllMonsters() {
        List<Monster> monsters = monsterMapper.findAllMonsters();
        for (Monster monster : monsters) {
            System.out.println(monster);
        }
        // 因为是select语句，所以不需要提交，但是仍然需要关闭连接
        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("查询成功!");
    }
}
