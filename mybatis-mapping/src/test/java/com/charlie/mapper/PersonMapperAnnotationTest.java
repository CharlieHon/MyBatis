package com.charlie.mapper;

import com.charlie.entity.Person;
import com.charlie.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

public class PersonMapperAnnotationTest {
    private SqlSession sqlSession;
    private PersonMapperAnnotation personMapperAnnotation;

    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
        personMapperAnnotation = sqlSession.getMapper(PersonMapperAnnotation.class);
    }

    @Test
    public void getPersonById() {
        Person person = personMapperAnnotation.getPersonById(1);
        System.out.println("person=" + person);

        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("OK!");
    }
}
