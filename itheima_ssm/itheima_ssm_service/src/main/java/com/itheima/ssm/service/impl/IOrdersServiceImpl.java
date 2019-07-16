package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IOrdersDao;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IOrdersServiceImpl implements IOrdersService {
    @Autowired
    private IOrdersDao iOrdersDao;

    @Override
    public List<Orders> findAll(Integer page, Integer pageSize) throws Exception {

        //查询前设置分页参数
        PageHelper.startPage(page,pageSize);
        //查询到具体该页的内容 ( 相当于给查询语句上拼接了上面两个参数 , 组成了分页查询语句 )
        List<Orders> ordersList = iOrdersDao.findAll();
        return ordersList;
    }

    @Override
    public Orders findById(String id) throws Exception {
        //查询到id对应的订单详情数据
        //在dao中 , 根据注解查询各id对应的实体数据
        Orders orders = iOrdersDao.findById(id);
        return orders;
    }
}
