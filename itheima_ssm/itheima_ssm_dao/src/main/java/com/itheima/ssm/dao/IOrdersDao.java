package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Member;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {

    /**
     * 查询订单信息
     * @return
     * @throws Exception
     */
    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class ,one = @One(select = "com.itheima.ssm.dao.IProductDao.findById"))
    })
    public List<Orders> findAll()throws Exception;


    /**
     * 根据id查询订单详情
     * @param id
     * @return
     */
    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id = true , property = "id", column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderStatus",column = "orderStatus"),
            //根据关联的productId查询对应的产品信息 ,
            @Result(property = "product",column = "productId",javaType = Product.class,
                one = @One(select = "com.itheima.ssm.dao.IProductDao.findById")
            ),
            //根据关联memberId查询对应的产品信息 ,
            @Result(property = "member",column = "memberId",javaType = Member.class,
                    one = @One(select = "com.itheima.ssm.dao.IMemberDao.findById")
            ),
            //产品表和旅客表是多对多关系, 需通过中间表查询
            @Result(property = "travellers",column = "id",javaType = List.class,
                    many = @Many(select = "com.itheima.ssm.dao.ITravellerDao.findById")
            )

    })
    Orders findById(String id) throws Exception;
}
