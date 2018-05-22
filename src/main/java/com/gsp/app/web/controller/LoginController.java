package com.gsp.app.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.gsp.app.model.GspMenu;
import com.gsp.app.model.ResponseVo;
import com.gsp.app.model.User;
import com.gsp.app.serives.MyService;
import com.gsp.app.vo.GspMenuVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * fengzhix.xu on 2018/5/19.
 */

@Controller
@RequestMapping("/user")
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private MyService myService;


    /**
     * 验证登录
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public Object login(User user) {
        try {

            if (1 == 1) {
                return ResponseVo.ofSuccess();
            }

            if (!checkUser(user)) {
                return ResponseVo.ofErrorMessage("用户或密码不能为空！");
            }

            if (!myService.isLoginSUc(user)) {
                return ResponseVo.ofErrorMessage("用户名密码错误！");
            }

            return ResponseVo.ofSuccess(user);

        } catch (Exception e) {
            logger.error("login error", e);
            return ResponseVo.ofErrorMessage();
        }
    }

    @RequestMapping(value = "/treeView")
    @ResponseBody
    public Object index(@RequestParam(value = "id") String id) {
        try {

            if (StringUtils.isNotBlank(id)) {
                List<GspMenuVo> gspMenus = myService.getMenuById(id);
                return CollectionUtils.isEmpty(gspMenus) ?
                        ResponseVo.ofErrorMessage() : JSONObject.toJSONString(ResponseVo.ofSuccess(gspMenus));
            }

        } catch (Exception e) {
            logger.error("query index error", e);
        }
        return ResponseVo.ofErrorMessage();
    }


    private boolean checkUser(User user) {
        return user == null
                || StringUtils.isBlank(user.getUser())
                || StringUtils.isBlank(user.getPassword());
    }

    public static void main(String[] args) {
        List<GspMenu> list = Lists.newArrayList();
    }


}
