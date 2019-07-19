package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有权限信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findALl() throws Exception {
        List<Permission> permissions =  permissionService.findAll();

        PageInfo pageInfo = new PageInfo(permissions);

        ModelAndView mav = new ModelAndView();
        mav.addObject("pageInfo",pageInfo);
        mav.setViewName("permission-page-list");
        return mav;
    }

    /**
     * 添加权限信息
     * @param permission
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public String save(Permission permission) throws Exception {
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

}
