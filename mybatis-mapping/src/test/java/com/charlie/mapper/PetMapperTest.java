package com.charlie.mapper;

import com.charlie.entity.Pet;
import com.charlie.entity.User;
import com.charlie.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PetMapperTest {
    private SqlSession sqlSession;
    private PetMapper petMapper;

    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
        petMapper = sqlSession.getMapper(PetMapper.class);
    }

    @Test
    public void getPetByUserId() {
        List<Pet> pets = petMapper.getPetByUserId(1);
        for (Pet pet : pets) {
            System.out.println("pet=" + pet);
        }

        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("OK!");
    }

    @Test
    public void getPetById() {
        Pet pet = petMapper.getPetById(4);
        System.out.println("pet=" + pet);
        User user = pet.getUser();
        System.out.println("user=" + user);

        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("OK!");
    }
}
