package com.yh.service;

import com.yh.mapper.BrandMapper;
import com.yh.mapper.UserMapper;
import com.yh.pojo.Brand;
import com.yh.pojo.User;
import com.yh.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserService {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();


    /***
     * 登陆方法
     * @param username
     * @param password
     * @return
     */
    public User login(String username,String password){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.select(username, password);
        sqlSession.close();
        return user;
    }


    public boolean register(User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
       //判断用户名是否存在
        User u = mapper.selectByUsername(user.getUsername());
        if(u==null){
            //用户名不存在
            mapper.add(user);
            sqlSession.commit();
        }

        sqlSession.close();
        return u==null;
    }

}
