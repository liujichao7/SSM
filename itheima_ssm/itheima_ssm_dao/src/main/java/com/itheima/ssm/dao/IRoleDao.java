package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IRoleDao {
    /**
     * 根据用户id查询角色信息
     * @param userId
     * @return
     * @throws Exception
     */
    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = List.class,
                    many = @Many(select = "com.itheima.ssm.dao.PermissionDao.findByRoleId")
            )
    })
    public List<Role> findById(String userId) throws Exception;


    /**
     * 查询所有角色信息
     * @return
     */
    @Select("select * from role")
    List<Role> findAll() throws Exception;

    /**
     * 添加角色
     * @param role
     */
    @Insert("insert into role (roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;

    /**
     * 根据角色id查询角色信息
     * @param id
     * @return
     */
    @Select("select * from role where id = #{id}")
    Role findByRoleId(String id) throws Exception;

    /**
     * 根据roleid查询该角色不具有的权限信息
     * @param roleId
     * @return
     */
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findOtherpermissionByRoleId(String roleId) throws Exception;

    /**
     * 向角色中添加权限
     * @param roleId
     * @param permissionId
     */
    @Insert("insert into role_permission (roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId) throws Exception;
}
