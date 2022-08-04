package com.yh.service;

import com.yh.mapper.BrandMapper;
import com.yh.pojo.Brand;
import com.yh.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandService {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /***
     * 查询所有
     * @return
     */
    public List<Brand> selectAll(){
        //调用BrandMapper.selectAll()

        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.selectAll();
        sqlSession.close();
        return  brands;
    }

    /***
     * 添加
     * @param brand
     */
    public void add(Brand brand) {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用添加方法
        mapper.add(brand);

        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    /***
     * 根据id查询
     * @param id
     * @return
     */
    public Brand selectById(int id){
        //调用BrandMapper.selectAll()

        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        Brand brand = mapper.selectById(id);
        sqlSession.close();
        return  brand;
    }


    /***
     * 修改
     * @param brand
     */
    public void update(Brand brand) {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用添加方法
        mapper.update(brand);

        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }


    public void delete(int id) {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用添加方法
        mapper.delete(id);

        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }
}
