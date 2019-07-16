package com.itheima.ssm.contriller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import java.awt.*;
import java.util.List;


@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 查询所有角色
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page" ,required = true,defaultValue = "1")Integer page,
                                @RequestParam(name = "pageSize" ,required = true,defaultValue = "4")Integer pageSize
                                ) throws Exception {
       List<Role> roleList = roleService.findAll(page,pageSize);
        PageInfo pageInfo = new PageInfo(roleList);

        ModelAndView mav = new ModelAndView();
        mav.addObject("pageInfo",pageInfo);
        mav.setViewName("role-page-list");
        return mav;
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @RequestMapping("/save.do")
    public String save(Role role) throws Exception{
        roleService.save(role);
        return "redirect:findAll.do";
    };

}
