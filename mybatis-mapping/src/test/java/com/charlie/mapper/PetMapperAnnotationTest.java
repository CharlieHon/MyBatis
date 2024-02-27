package com.charlie.mapper;

import com.charlie.entity.Pet;
import com.charlie.entity.User;
import com.charlie.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PetMapperAnnotationTest {
    private SqlSession sqlSession;
    private PetMapperAnnotation petMapperAnnotation;

    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
        petMapperAnnotation = sqlSession.getMapper(PetMapperAnnotation.class);
    }

    @Test
    public void getPetByUserId() {
        List<Pet> pets = petMapperAnnotation.getPetByUserId(1);
        for (Pet pet : pets) {
            User user = pet.getUser();
            System.out.println("pet=" + pet);
            System.out.println("user=" + user);
        }

        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("OK!");
    }

    @Test
    public void getPetById() {
        Pet pet = petMapperAnnotation.getPetById(2);
        User user = pet.getUser();
        System.out.println("pet=" + pet);
        System.out.println("user=" + user);

        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("OK!");
    }
}
