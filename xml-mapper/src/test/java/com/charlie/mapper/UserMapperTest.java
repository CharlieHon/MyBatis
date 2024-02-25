package com.charlie.mapper;

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
    public void addUser() {
        User user = new User();
        user.setUsername("tom");
        user.setUseremail("tom@sohu.com");
        userMapper.addUser(user);

        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }

        System.out.println("OK!");
    }

    @Test
    public void findAllUser() {
        List<User> users = userMapper.findAllUser();
        for (User user : users) {
            System.out.println("user=" + user);
        }

        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("OK!");
    }
}
