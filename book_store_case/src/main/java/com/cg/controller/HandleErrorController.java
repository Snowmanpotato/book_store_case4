package com.cg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class HandleErrorController {
    @GetMapping("/404")
    public String showError403Page(){
        return "/error/404";
    }
}