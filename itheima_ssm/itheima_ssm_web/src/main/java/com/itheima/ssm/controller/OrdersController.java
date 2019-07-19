package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 *
 * 查询订单信息
 *      分析:
 *
 *
 *          1. 在侧导航栏点击订单管理 , 进行访问
 *          2. controller中, 调用service进行访问 . 将访问到的结果集合, 进行封装, 跳转到orders-list页面, 进行数据展示
 *          3. service层调用dao进行访问
 *          4. dao接口定义访问方法, 并用注解的方法进行数据查询@select
 *              该注解中,实现表中字段名和实体类属性的映射, 产品信息通过订单表中的产品关联id进行产品查询 , 然后进行映射
 *          5. 定义实体类
 *              该订单类, 订单表会对应多张表 , 所以直接将该表对应的实体类或集合定义为属性
 *              注意: 表中日期和页面中日期的类型转换
 *                    表中的订单状态和支付方式在页面的展示类型
 *          6. 页面数据展示
 *              利用adminLTE进行页面的处理 , (all-admin-dataList,)
 *              根据页面需要进行数据的展示
 *  三层架构的配置
 *  *      1.mybatis: applicationContext.xml : 组件扫描(注解), 数据源, SQLSessionFactory工厂(定义别名),加载映射
 *  *                  数据库配置文件, log4j 日志配置文件
 *  *      2.spring: applicationContext.xml: 组件扫描, 事务管理(平台事务管理器,加载注解驱动) , 配置自动完成创建代理织入切面
 *  *      3.springmvc: spring-mvc.xml: 组件扫描,视图解析,注解映射 , 释放静态资源
 *  *                  web.xml: 编码过滤器, 前端控制器(springmvc),spring监听器(注意扫描路径问题, 要保证能扫到所有的applicationContext文件)
 *  *
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService iOrdersService;
/*
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        List<Orders> ordersList = iOrdersService.findAll();

        ModelAndView mav = new ModelAndView();
        mav.addObject("ordersList",ordersList);
        mav.setViewName("orders-list");
        return mav;
    }
    */

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                                @RequestParam(name = "pageSize",required = true,defaultValue = "4")Integer pageSize
                                ) throws Exception {
        List<Orders> ordersList = iOrdersService.findAll(page,pageSize);

        //将查询到的分页集合封装到pageInfo中
        PageInfo pageInfo = new PageInfo(ordersList);

        ModelAndView mav = new ModelAndView();
        mav.addObject("pageInfo",pageInfo);
        mav.setViewName("orders-page-list");
        return mav;
    }


    /**
     * 查询单个订单详情
     * @param id
     * @return
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String id) throws Exception {
        Orders orders = iOrdersService.findById(id);

        ModelAndView mav = new ModelAndView();
        mav.addObject("orders",orders);
        mav.setViewName("order-show");
        return mav;
    }

}
