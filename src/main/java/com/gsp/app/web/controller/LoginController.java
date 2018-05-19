package com.gsp.app.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by finup on 2018/5/19.
 */
@Controller
@RequestMapping
public class LoginController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String main() {
        System.out.println("xxxxx");
        return "login";
    }

    @RequestMapping("user")
    public String user() {
        return "user/user";
    }
}
