package com.itheima.ssm.domain;

import java.util.List;

/*
    角色表
        1. 和用户表UserInfo的多对多
            由中间表的 userId-roleId 对应
        2. 和权限表的多对多
            由中间表的 roleId-permissionId 对应
 */
public class Role {
    private String id;
    private String roleName;  //角色名
    private String roleDesc;  //角色描述

    private List<UserInfo>  userInfos;
    private List<Permission> permissions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", userInfos=" + userInfos +
                ", permissions=" + permissions +
                '}';
    }
}
