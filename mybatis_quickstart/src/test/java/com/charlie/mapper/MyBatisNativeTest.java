package com.charlie.mapper;

import com.charlie.entity.Monster;
import com.charlie.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

// 演示使用MyBatis原生API操作
public class MyBatisNativeTest {
    private SqlSession sqlSession;

    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
        // sqlSession返回的对象是DefaultSqlSession
        System.out.println("sqlSession=" + sqlSession.getClass());
    }

    @Test
    public void myBatisNativeCrud() {
        // 添加
        /**
         *     public int insert(String statement, Object parameter) {
         *         return this.update(statement, parameter);
         *     }
         *     statement:就是接口方法的完整声明
         *     parameter：传入参数
         */
        //Monster monster = new Monster();
        //monster.setAge(10);
        //monster.setBirthday(new Date());
        //monster.setEmail("kiki@qq.com");
        //monster.setGender(0);
        //monster.setName("黄风怪");
        //monster.setSalary(1010.0);
        //
        //int insert = sqlSession.insert("com.charlie.mapper.MonsterMapper.addMonster", monster);
        //System.out.println("insert=" + insert);

        // 删除
        //int delete = sqlSession.delete("com.charlie.mapper.MonsterMapper.delMonster", 6);
        //System.out.println("delete=" + delete);

        // 修改
        //Monster monster = new Monster();
        //monster.setId(5);       // 要有id，不然不知道要修改哪个数据
        //monster.setAge(10);
        //monster.setBirthday(new Date());
        //monster.setEmail("kiki@qq.com");
        //monster.setGender(0);
        //monster.setName("黄风怪");
        //monster.setSalary(1010.0);
        //int update = sqlSession.update("com.charlie.mapper.MonsterMapper.updateMonster", monster);
        //System.out.println("update=" + update);

        // 查询
        List<Monster> monsters = sqlSession.selectList("com.charlie.mapper.MonsterMapper.findAllMonsters");
        for (Monster monster : monsters) {
            System.out.println("monster=" + monster);
        }

        // 如果是增删改，需要提交事务
        if (sqlSession != null) {
            sqlSession.commit();    // 查询的时候，可以不用提交
            sqlSession.close();
        }
        System.out.println("保存成功...");
    }
}
