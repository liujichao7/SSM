package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.SysLogDao;
import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    /**
     * 添加日志记录
     * @param sysLog
     */
    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    /**
     * 查询日志
     * @return
     * @param page
     * @param pageSize
     */
    @Override
    public List<SysLog> findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        return sysLogDao.findAll();
    }
}
