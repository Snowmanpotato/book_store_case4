package com.cg.controller;

import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    AppUtils appUtils;

    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        String username = appUtils.getPrincipalUsername();
        System.out.println(username);
        if (username.equals("anonymousUser")) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("login/login");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/products");
//        modelAndView.addObject("username", username);
        return modelAndView;
    }

//    @GetMapping("/")
//    public String showIndex() {
//        return "redirect:/products";
//    }
//
//
//    @GetMapping("/login")
//    public String login() {
//      return  "login/login";
//    }
//
//    @GetMapping("/register")
//    public ModelAndView register() {
//        return new ModelAndView("login/login");
//    }


}