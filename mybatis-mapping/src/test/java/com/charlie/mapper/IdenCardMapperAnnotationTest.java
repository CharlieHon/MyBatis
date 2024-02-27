package com.charlie.mapper;

import com.charlie.entity.IdenCard;
import com.charlie.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

public class IdenCardMapperAnnotationTest {
    private SqlSession sqlSession;
    private IdenCardMapperAnnotation idenCardMapperAnnotation;

    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
        idenCardMapperAnnotation = sqlSession.getMapper(IdenCardMapperAnnotation.class);
    }

    @Test
    public void getIdenCardById() {
        IdenCard idenCard = idenCardMapperAnnotation.getIdenCardById(1);
        System.out.println("idenCard=" + idenCard);

        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("OK!");
    }
}
