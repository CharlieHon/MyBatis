package com.charlie.mapper;

import com.charlie.entity.Pet;
import com.charlie.entity.User;
import com.charlie.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UserMapperAnnotationTest {
    private SqlSession sqlSession;
    private UserMapperAnnotation userMapperAnnotation;

    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
        userMapperAnnotation = sqlSession.getMapper(UserMapperAnnotation.class);
    }

    @Test
    public void getUserById() {
        User user = userMapperAnnotation.getUserById(2);
        List<Pet> pets = user.getPets();
        System.out.println("user=" + user);
        System.out.println("pets=" + pets);

        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("OK!");
    }
}
