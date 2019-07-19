package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class IProductController {

    @Autowired
    private IProductService iProductService;

    /**
     * 查询产品信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    @RolesAllowed("USER")       //限制访问该方法应具有的角色名  (jsr250注解方式)
//    @PreAuthorize("hasRole('USER')")
    public ModelAndView findAll() throws Exception {
        List<Product> productList = iProductService.findAll();
        ModelAndView mav = new ModelAndView();
        mav.addObject("productList",productList);
        mav.setViewName("product-list");

        return mav;
    }

    /**
     * 添加产品
     * @param product
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
        iProductService.save(product);
        return "redirect:findAll.do";
    }
    ;

}
