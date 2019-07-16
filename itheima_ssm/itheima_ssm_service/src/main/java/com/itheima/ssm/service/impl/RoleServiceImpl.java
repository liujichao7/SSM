package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IRoleDao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private IRoleDao iRoleDao;

    /**
     * 查询所有角色信息
     * @return
     * @param page
     * @param pageSize
     */
    @Override
    public List<Role> findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        return iRoleDao.findAll();
    }

    /**
     * 添加角色
     * @param role
     * @throws Exception
     */
    @Override
    public void save(Role role) throws Exception {
        iRoleDao.save(role);
    }
}
