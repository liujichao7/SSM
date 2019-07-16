package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {
    /**
     * 根据角色id查询权限信息
     * @param roleId
     * @return
     */
    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{roleId})")
    public List<Permission> findByRoleId(String roleId);


    /**
     * 查询所有权限资源
     * @return
     */
    @Select("select * from permission")
    List<Permission> findAll() throws Exception;

    /**
     * 添加权限
     * @throws Exception
     */
    @Insert("insert into permission (permissionName,url) values (#{permissionName},#{url})")
    void save(Permission permission) throws  Exception;
}
