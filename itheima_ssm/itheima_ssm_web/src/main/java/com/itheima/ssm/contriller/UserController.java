package com.itheima.ssm.contriller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * 查询所有用户
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
            @RequestParam(name = "pageSize",required = true,defaultValue = "4")Integer pageSize){
        List<UserInfo> users =  iUserService.findALL(page,pageSize);
        PageInfo pageInfo = new PageInfo(users);
        ModelAndView mav = new ModelAndView();
        mav.addObject("pageInfo",pageInfo);
        mav.setViewName("user-page-list");
        return mav;
    }

    /**
     * 添加用户
     * @param userInfo
     * @return
     * @throws Exception
     *
     * 流程:    1.点击添加
     *          2.controller接收请求 , 调用service添加
     *          3.service对参数中的密码进行加密 , 并重新赋给对象 , 调用dao继续添加
     *              @ : spring-security配置文件中配置加密类, 并告知认证操作的service 加密方式(登录时需要此配置)
     *          4.dao层根据注解进行添加操作
     */
    @RequestMapping("/save.do")
    public String  save(UserInfo userInfo) throws Exception{
        iUserService.save(userInfo);
        return "redirect:findAll.do";
    }
@RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true)String id){
        UserInfo userInfo = iUserService.findById(id);
        ModelAndView mav = new ModelAndView();
        mav.addObject("user",userInfo);
        mav.setViewName("user-show");
        return mav;
    }
}
