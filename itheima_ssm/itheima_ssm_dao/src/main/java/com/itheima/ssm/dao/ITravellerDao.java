package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellerDao {
//多表查询
    //原始参数是orderID
    //根据该id查询出中间表中对应的travellerID
    //然后根据该travellersId 在travellers表中对应的主键id查询出对应的旅客表信息
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId = #{id}) ")
    List<Traveller> findById(String id)throws Exception;

}
