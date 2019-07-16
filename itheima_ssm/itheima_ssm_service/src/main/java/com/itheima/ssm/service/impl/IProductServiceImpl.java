package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IProductDao;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class IProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao iProductDao;

    /**
     * 查询所有产品信息
     * @return
     */
    @Override
    public List<Product> findAll() throws Exception {
        List<Product> productList = iProductDao.findAll();
        return productList;
    }

    /**
     * 添加产品
     * @param product
     * @throws Exception
     */
    @Override
    //事务提交
    @Transactional
    public void save(Product product) throws Exception {
        iProductDao.save(product);
    }
}
