package com.itheima.ssm.service;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

//继承这个接口 来让其由spring-security框架管理
public interface IUserService extends UserDetailsService {

    List<UserInfo> findALL(Integer page, Integer pageSize) throws Exception;

    void save(UserInfo userInfo) throws Exception;

    UserInfo findById(String id) throws Exception;

    List<Role> findOtherRoleById(String id) throws Exception;

    void addRoleToUser(String userId, String[] roleIds) throws Exception;
}
