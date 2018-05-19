package com.gsp.app.web.controller;

import com.gsp.app.constant.ErrorEnum;
import com.gsp.app.context.AppContext;
import com.gsp.app.model.Response;
import com.gsp.app.model.User;
import com.gsp.app.serives.MyService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * fengzhix.xu on 2018/5/19.
 */

@Controller
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private MyService myService;


    /**
     * 验证登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(User user) {
        try {

            if (!checkUser(user)) {
                return Response.fail(ErrorEnum.USER_EMPTY);
            }

            if (!myService.isLoginSUc(user)) {
                logger.error("login error {}:", user.toString());
                return Response.fail(ErrorEnum.PWD_ERROR);
            }

            return Response.suc(user);

        } catch (Exception e) {
            logger.error("login error", e);
            return Response.fail(ErrorEnum.FAIL);
        } finally {
            AppContext.clear();
        }
    }


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(String id) {
        try {

            if (StringUtils.isNotBlank(id)) {
                return "";
            }

        } catch (Exception e) {
            logger.error("query index error", e);
        }
        return Response.fail(ErrorEnum.FAIL);
    }


    private boolean checkUser(User user) {
        return user == null
                || StringUtils.isBlank(user.getUserName())
                || StringUtils.isBlank(user.getPassWord());
    }


}