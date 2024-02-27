package com.charlie.mapper;

import com.charlie.entity.Person;
import com.charlie.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

public class PersonMapperTest {
    private SqlSession sqlSession;
    private PersonMapper personMapper;

    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
        personMapper = sqlSession.getMapper(PersonMapper.class);
    }

    @Test
    public void getPersonById() {
        Person person = personMapper.getPersonById(1);
        System.out.println("person=" + person);

        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("OK!");
    }

    @Test
    public void getPersonById2() {
        Person person = personMapper.getPersonById2(1);
        System.out.println("person=" + person);

        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("OK!");
    }
}
