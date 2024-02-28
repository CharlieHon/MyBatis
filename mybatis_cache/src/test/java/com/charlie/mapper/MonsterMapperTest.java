package com.charlie.mapper;

import com.charlie.entity.Monster;
import com.charlie.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MonsterMapperTest {
    private SqlSession sqlSession;
    private MonsterMapper monsterMapper;

    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);
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

    @Test
    public void level1CacheTest() {
        Monster monster = monsterMapper.getMonsterById(5);
        System.out.println("查询结果：" + monster);
        monsterMapper.getMonsterById(7);

        // 当再次查询id为5的monster，直接从缓存中获取
        System.out.println("===***因为一级缓存默认是打开的，当两次查询的id相同时，不会再次发出SQL语句***===");
        Monster monster2 = monsterMapper.getMonsterById(5);
        System.out.println("查询结果：" + monster2);

        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("查询成功!");
    }

    // 一级缓存失效情况1：关闭sqlSession
    @Test
    public void level1CacheTest2() {
        Monster monster = monsterMapper.getMonsterById(5);
        System.out.println("查询结果：" + monster);

        // 关闭sqlSession，一级缓存失效
        if (sqlSession != null) {
            sqlSession.close();
        }

        // 因为关闭了sqlSession，所以下面需要重新初始化sqlSession和monsterMapper
        sqlSession = MyBatisUtils.getSqlSession();
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);

        // 当再次查询id为5的monster，直接从缓存中获取
        System.out.println("===***如果关闭sqlSession，当再次查询相同的id时，仍然会发出sql语句***===");
        Monster monster2 = monsterMapper.getMonsterById(5);
        System.out.println("查询结果：" + monster2);

        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("查询成功!");
    }

    // 一级缓存失效情况2：sqlSession.clearCache()
    @Test
    public void level1CacheTest3() {
        Monster monster = monsterMapper.getMonsterById(5);
        System.out.println("查询结果：" + monster);

        // 如果执行了 sqlSession.clearCache()，一级缓存也会失效
        sqlSession.clearCache();

        // 当再次查询id为5的monster，直接从缓存中获取
        System.out.println("===***sqlSession.clearCache()，一级缓存会失效***===");
        Monster monster2 = monsterMapper.getMonsterById(5);
        System.out.println("查询结果：" + monster2);

        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("查询成功!");
    }

    // 一级缓存失效情况2：修改查询结果对象
    @Test
    public void level1CacheTest4() {
        Monster monster = monsterMapper.getMonsterById(5);
        System.out.println("查询结果：" + monster);

        // 修改查询的结果，一级缓存也会失效
        monster.setName("黄袍怪");
        monsterMapper.updateMonster(monster);

        // 当再次查询id为5的monster，直接从缓存中获取
        System.out.println("===***如果修改了查询结果对象，再次查询相同id时仍会发送sql语句，一级缓存会失效***===");
        Monster monster2 = monsterMapper.getMonsterById(5);
        System.out.println("查询结果：" + monster2);

        if (sqlSession != null) {
            sqlSession.commit();    // 因为有修改，需要commit
            sqlSession.close();
        }
        System.out.println("查询成功!");
    }

    // 测试二级缓存使用
    @Test
    public void level2CacheTest() {
        Monster monster = monsterMapper.getMonsterById(5);
        System.out.println("查询结果：" + monster);

        // 关闭sqlSession
        if (sqlSession != null) {
            sqlSession.close();
        }
        // 重新获取sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);

        // 当再次查询id为5的monster
        System.out.println("===***虽然前面关闭了sqlSession，因为配置了二级缓存，当再次查询相同id时依然不会发出SQL***===");
        Monster monster2 = monsterMapper.getMonsterById(5);
        System.out.println("查询结果：" + monster2);
        System.out.println("===============");
        monsterMapper.getMonsterById(5);

        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("查询成功!");
    }

    // 缓存执行顺序：二级缓存->一级缓存->DB
    @Test
    public void cacheSeqTest() {
        // 第一次查询，从DB获取，发出SQL，ratio：0.0
        System.out.println("查询第1次");
        Monster monster1 = monsterMapper.getMonsterById(5);
        System.out.println("查询结果：" + monster1);

        // 关闭sqlSession，一级缓存数据没有
        // 当关闭一级缓存时，如果配置/开启了二级缓存，那么一级缓存的数据会放入二级缓存
        if (sqlSession != null) {
            sqlSession.close();
        }
        sqlSession = MyBatisUtils.getSqlSession();
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);

        // 从二级缓存获取数据。不会发出SQL，ratio：0.5
        System.out.println("查询第2次");
        Monster monster2 = monsterMapper.getMonsterById(5);
        System.out.println("查询结果：" + monster2);

        // 从二级缓存获取数据，不会发出SQL，ratio：0.6666
        System.out.println("查询第3次");
        Monster monster3 = monsterMapper.getMonsterById(5);
        System.out.println("查询结果：" + monster3);

        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("OK!");
    }

    // 分析缓存执行顺序
    // 二级缓存->一级缓存->DB
    @Test
    public void cacheSeqTest2() {
        // 第一次查询，从DB获取，发出SQL，ratio：0.0
        System.out.println("查询第1次");
        Monster monster1 = monsterMapper.getMonsterById(5);
        System.out.println("查询结果：" + monster1);

        // 没有关闭sqlSession，二级缓存数据是在一级缓存关闭后才有
        // 这里仍从一级缓存获取，不会发出SQL，ratio：0.0
        System.out.println("查询第2次");
        Monster monster2 = monsterMapper.getMonsterById(5);
        System.out.println("查询结果：" + monster2);

        // 依然从一级缓存获取数据，不会发出SQL，ratio：0.0
        System.out.println("查询第3次");
        Monster monster3 = monsterMapper.getMonsterById(5);
        System.out.println("查询结果：" + monster3);

        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("OK!");
    }

    // 测试ehcache
    @Test
    public void ehcacheTest() {
        // 第一次查询，从DB获取，发出SQL
        System.out.println("查询第1次");
        Monster monster1 = monsterMapper.getMonsterById(5);
        System.out.println("查询结果：" + monster1);

        // 关闭sqlSession，一级缓存数据失效，将数据放入到二级缓存(ehcache)
        if (sqlSession != null) {
            sqlSession.close();
        }
        sqlSession = MyBatisUtils.getSqlSession();
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);

        // 从二级缓存(ehcache)获取数据。不会发出SQL，ratio：0.5
        System.out.println("查询第2次");
        Monster monster2 = monsterMapper.getMonsterById(5);
        System.out.println("查询结果：" + monster2);

        // 从二级缓存(ehcache)获取数据，不会发出SQL，ratio：0.6666
        System.out.println("查询第3次");
        Monster monster3 = monsterMapper.getMonsterById(5);
        System.out.println("查询结果：" + monster3);

        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("OK!");
    }
}
