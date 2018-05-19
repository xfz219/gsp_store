package com.gsp.app.web.controller;

import com.gsp.app.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * fengzhix.xu on 2018/5/19.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    public String login(User user) {
        return "";
    }


}
