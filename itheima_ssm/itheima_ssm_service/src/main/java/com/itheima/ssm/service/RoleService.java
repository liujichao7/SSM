package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;

import java.util.List;

public interface  RoleService {

    List<Role> findAll(Integer page, Integer pageSize) throws Exception;

    void save(Role role) throws Exception;

    Role findById(String id) throws Exception;

    List<Permission> findOtherpermission(String id) throws Exception;

    void addPermissionToRole(String roleId, String[] permissionIds) throws Exception;
}
