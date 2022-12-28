package com.cg.controller;

import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private AppUtils appUtils;


    @GetMapping
    public ModelAndView showListPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product/index");

        modelAndView.addObject("user", appUtils.getPrincipalUser());

        return modelAndView;
    }

//    @GetMapping("/managerProduct")
//    public ModelAndView showListForm() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("/product/listProduct");
//        return modelAndView;
//    }


    @GetMapping("/createProduct")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/product/createProduct");
        return modelAndView;
    }


//    @GetMapping("/managerProduct/Update")
//    public ModelAndView showEditForm() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("product/modalUpdateProduct");
//        return modelAndView;
//    }
}