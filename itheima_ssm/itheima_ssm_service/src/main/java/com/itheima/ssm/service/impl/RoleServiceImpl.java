package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IRoleDao;
import com.itheima.ssm.domain.Permission;
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
    public List<Role> findAll(Integer page, Integer pageSize) throws Exception {
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

    /**
     * 根据id查询角色信息
     * @param id
     * @return
     */
    @Override
    public Role findById(String id) throws Exception {
        return iRoleDao.findByRoleId(id);
    }

    /**
     * 根据roleid查询该角色不具有的权限信息
     * @param id
     * @return
     */
    @Override
    public List<Permission> findOtherpermission(String id) throws Exception {
        return iRoleDao.findOtherpermissionByRoleId(id);
    }

    /**
     * 向角色中添加权限
     * @param roleId
     * @param permissionIds
     */
    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {
        for (String permissionId : permissionIds) {
            iRoleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
