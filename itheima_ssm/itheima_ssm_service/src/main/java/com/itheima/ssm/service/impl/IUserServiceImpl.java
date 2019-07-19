package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IUserDao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class IUserServiceImpl implements IUserService {
    @Autowired
    private IUserDao iUserDao;
    // UserDetails作用是于封装当前进行认证的用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询到userInfo信息
        UserInfo userInfo = null;
        try {
            userInfo = iUserDao.findByUserName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //获取该用户对应的角色集合
        List<Role> roles = userInfo.getRoles();

        //定义方法 , 将获取到的权限封装到框架所需的权限认证集合对象中
        List<SimpleGrantedAuthority> authoritys = getAuthority(roles);

        //UserDetails的user实现 , 参数有 用户名, 密码, 所具有的的权限 .(账户状态是否开启)
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),
                    userInfo.getStatus()==0?false:true,true,true,true ,authoritys);

        return user;
    }


    //作用就是返回一个List集合，集合中装入的是角色描述
    // SimpleGrantedAuthority  该泛型根据源码查看, 可知其需要的是一个权限名
    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        //将权限名赋给该泛型 <查询出的权限集合可通过遍历来赋值>
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    /**
     * 分页查询所有用户信息
     * @return
     * @param page
     * @param pageSize
     */
    @Override
    public List<UserInfo> findALL(Integer page, Integer pageSize) throws Exception {
        PageHelper.startPage(page,pageSize);
        return  iUserDao.findAll();
    }

    //注入加密类
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public void save(UserInfo userInfo) throws Exception {
        //对密码进行加密
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));

        iUserDao.save(userInfo);
    }

    /**
     * 查询用户详情
     * @param id
     * @return
     */
    @Override
    public UserInfo findById(String id) throws Exception {
        return iUserDao.findById(id);
    }

    /**
     * 根据userID查询所不具有的角色信息
     * @param id
     * @return
     */
    @Override
    public List<Role> findOtherRoleById(String id) throws Exception {
        return iUserDao.findOtherRoleById(id);
    }

    /**
     * 添加角色到用户
     * @param userId
     * @param roleIds
     */
    @Override
    public void addRoleToUser(String userId, String[] roleIds) throws Exception {
        for (String roleId : roleIds) {
            iUserDao.addRoleToUser(userId,roleId);
        }
    }
}
