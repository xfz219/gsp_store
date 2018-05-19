package com.gsp.app.web.controller;

import com.gsp.app.model.Response;
import com.gsp.app.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * fengzhix.xu on 2018/5/19.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    public String login(User user) {
        try {

        } catch (Exception e) {
            logger.error("");
        }
        return Response.suc(null);
    }


}
