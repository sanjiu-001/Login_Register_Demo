package com.yh.mapper;

import com.yh.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {


    /****
     * 查询所有
     * @return
     */
    @Select("select *from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    /***
     * 添加
     * @param brand
     */
    @Insert("insert into tb_brand values(null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);


    /***
     * 根据id查询
     * @param id
     * @return
     */
    @Select("select *from tb_brand where id=#{id}")
    @ResultMap("brandResultMap")
    Brand selectById(int id);


    /***
     * 修改
     * @param brand
     */
    @Update("update tb_brand set brand_name=#{brandName},company_name=#{companyName},ordered=#{ordered},description=#{description},status=#{status},brand_name=#{brandName} where id=#{id}")
    void update(Brand brand);


    /***
     * 删除
     * @param id
     */
    @Delete("delete from tb_brand where id=#{id}")
    void delete(int id);
}
