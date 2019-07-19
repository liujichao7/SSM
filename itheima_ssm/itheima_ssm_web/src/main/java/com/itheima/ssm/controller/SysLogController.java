package com.itheima.ssm.controller;


import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,value = "1") Integer page,
                                @RequestParam(name = "pageSize",required = true,value = "4") Integer pageSize){
        List<SysLog> sysLogList = sysLogService.findAll(page,pageSize);
        PageInfo pageInfo = new PageInfo(sysLogList);

        ModelAndView mav = new ModelAndView();
        mav.addObject("pageInfo",pageInfo);
        mav.setViewName("sysLog-page-list");
        return mav;
    }
}
