package com.charlie.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

// 这是一个工具类，可以得到SqlSession
public class MyBatisUtils {
    // 会话工厂，根据配置文件创建工厂，可以创建SqlSession(会话，类似一个连接)
    private static SqlSessionFactory sqlSessionFactory;

    // 编写静态代码块，初始化sqlSessionFactory
    static {
        try {
            // 指定资源文件，即配置文件
            String resource = "mybatis-config.xml";
            // 获取到配置文件对应的流，加载文件时，默认到resources目录下
            InputStream resourceAsStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            // sqlSessionFactory=class org.apache.ibatis.session.defaults.DefaultSqlSessionFactory
            System.out.println("sqlSessionFactory=" + sqlSessionFactory.getClass());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 编写方法，返回SqlSession对象-会话，操作数据库DB(发出SQL语句，完成增删改查)
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
