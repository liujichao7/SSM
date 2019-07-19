package com.itheima.ssm.dao;

import com.itheima.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysLogDao {

    /**
     * 添加日志记录
     * @param sysLog
     */
    @Insert("insert into sysLog (visiTime,username,ip,url,executionTime,method) values(#{visiTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog);

    /**
     * 查询日志列表
     * @return
     */
    @Select("select * from sysLog")
    List<SysLog> findAll();
}
