package com.charlie.mapper;

import com.charlie.entity.IdenCard;
import com.charlie.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

public class IdenCardMapperTest {
    private SqlSession sqlSession;
    private IdenCardMapper idenCardMapper;

    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
        idenCardMapper = sqlSession.getMapper(IdenCardMapper.class);
    }

    @Test
    public void getIdenCardById() {
        IdenCard idenCard = idenCardMapper.getIdenCardById2(200);
        System.out.println("idenCard=" + idenCard);

        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("OK!");
    }
}
