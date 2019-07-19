package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    @Select("select * from users where username = #{username}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "email",column = "email"),
            @Result(property = "username",column = "username"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = List.class,
                    many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findById")
            )
    })
    public UserInfo findByUserName(String userName) throws Exception;

    /**
     * 查询所有用户信息
     * @return
     */
    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    /**
     * 添加用户
     * @param userInfo
     */
    @Insert("insert into users (username,password,email,phoneNum,status) values (#{username},#{password},#{email},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;

    /**
     * 查询用户详情
     * @param id
     * @return
     */
    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "email",column = "email"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = List.class,
                many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findById")
            ),
    })
    UserInfo findById(String id) throws Exception;

    /**
     * 根据userID查询所不具有的角色信息
     * @param id
     * @return
     */
    @Select("select * from role where id not in (select roleId  from users_role where userId = #{id})")
    List<Role> findOtherRoleById(String id) throws Exception;

    /**
     * 添加角色到用户中
     * @param userId
     * @param roleId
     */
    @Insert("insert into users_role (userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId) throws Exception;
}
