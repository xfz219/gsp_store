package com.gsp.app.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * fengzhix.xu on 2018/5/19.
 */
@Controller
public class PageController {


    @RequestMapping("/home")
    public String index() {
        return "index";
    }


    @RequestMapping("/login")
    public String login() {
        return "/user/login";
    }
}