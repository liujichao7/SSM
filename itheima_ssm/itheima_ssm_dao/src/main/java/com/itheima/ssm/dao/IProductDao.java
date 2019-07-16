package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.security.PublicKey;
import java.util.List;


public interface IProductDao {
    //查询所有产品信息
    @Select("select * from product")
    public List<Product> findAll() throws Exception;

    //添加商品操作
    @Insert("insert into product (productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values (#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product)throws Exception;

    //根据id查询
    @Select("select * from product where id = #{id}")
    public Product findById(String id) throws  Exception;
}
