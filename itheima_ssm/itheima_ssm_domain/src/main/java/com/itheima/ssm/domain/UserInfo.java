package com.itheima.ssm.domain;

import java.util.List;

/*
    用户类
        和角色表role存在多对多关系
        由中间表的 userId-roleId 对应
 */
public class UserInfo {
    private String id;
    private String email;   //邮箱
    private String username;  //用户名
    private String  password;  //密码
    private String  phoneNum;  //电话
    private Integer status;   //账户启用状态 0 未开启 1 开启
    private String statusStr;  //状态字符串

    //多对多关系形成的单方一对多 , 对应的role角色列表
    private List<Role> roles;





    @Override
    public String toString() {
        return "UsesInfo{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", status=" + status +
                ", statusStr='" + statusStr + '\'' +
                ", roles=" + roles +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getStatusStr() {
        if (status == 0){
            statusStr = "未开启";
        }else if (status == 1){
            statusStr = "开启";
        }
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
