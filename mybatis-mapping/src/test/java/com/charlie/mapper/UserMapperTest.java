package com.charlie.mapper;

import com.charlie.entity.Pet;
import com.charlie.entity.User;
import com.charlie.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UserMapperTest {
    private SqlSession sqlSession;
    private UserMapper userMapper;

    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void getUserById() {
        User user = userMapper.getUserById(2);
        System.out.println("user=" + user.getName());
        List<Pet> pets = user.getPets();
        for (Pet pet : pets) {
            System.out.println("pet=" + pet);
        }

        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("OK!");
    }
}
