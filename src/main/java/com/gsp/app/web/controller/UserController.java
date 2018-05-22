package com.gsp.app.web.controller;

import com.google.common.base.MoreObjects;
import com.gsp.app.model.GspUser;
import com.gsp.app.model.ResponseVo;
import com.gsp.app.serives.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * fengzhix.xu on 2018/5/19.
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/queryAll")
    public Object queryAllUser(GspUser pojo) {
        try {
            List<GspUser> userList = userService.selectAllUser(pojo);
            return CollectionUtils.isEmpty(userList) ? ResponseVo.ofErrorMessage() : ResponseVo.ofSuccess(userList);
        } catch (Exception e) {
            log.error("query all error", e);
        }
        return ResponseVo.ofErrorMessage();
    }


    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public Object updateUser(GspUser user) {
        try {

            if (!checkUser(user)) {
                return ResponseVo.ofErrorMessage();
            }
            return userService.updateUser(user) ? ResponseVo.ofSuccess() : ResponseVo.ofErrorMessage();
        } catch (Exception e) {
            log.error("update user error", e);
        }
        return ResponseVo.ofErrorMessage();
    }


    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public Object addUser(GspUser user) {
        try {
            if (!checkUser(user)) {
                return ResponseVo.ofErrorMessage();
            }
            return userService.addUser(user) ? ResponseVo.ofSuccess() : ResponseVo.ofErrorMessage();
        } catch (Exception e) {
            log.error("add user error", e);
        }
        return ResponseVo.ofErrorMessage();
    }


    @RequestMapping(value = "/queryOne", method = RequestMethod.GET)
    public Object queryOne(String id) {
        try {
            if (StringUtils.isNotBlank(id)) {
                GspUser user = userService.selectOne(id);
                return ResponseVo.ofSuccess(MoreObjects.firstNonNull(user, new GspUser()));
            }
        } catch (Exception e) {
            log.error("query one error", e);
        }
        return ResponseVo.ofErrorMessage();
    }


    private boolean checkUser(GspUser user) {
        return user == null
                || 0 == user.getId()
                || StringUtils.isBlank(user.getName());

    }

}
