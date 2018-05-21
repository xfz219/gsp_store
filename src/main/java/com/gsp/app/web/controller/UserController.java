package com.gsp.app.web.controller;

import com.google.common.base.MoreObjects;
import com.gsp.app.constant.ErrorEnum;
import com.gsp.app.model.GspUser;
import com.gsp.app.model.Response;
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
    public String queryAllUser(GspUser pojo) {
        try {
            List<GspUser> userList = userService.selectAllUser(pojo);
            return CollectionUtils.isEmpty(userList) ? Response.fail(ErrorEnum.FAIL) : Response.suc(userList);
        } catch (Exception e) {
            log.error("query all error", e);
        }
        return Response.fail(ErrorEnum.FAIL);
    }


    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUser(GspUser user) {
        try {

            if (!checkUser(user)) {
                return Response.fail(ErrorEnum.PARAM_EMPTY);
            }
            return userService.updateUser(user) ? Response.suc(ErrorEnum.SUC) : Response.fail(ErrorEnum.OPERATION_ERROR);
        } catch (Exception e) {
            log.error("update user error", e);
        }
        return Response.fail(ErrorEnum.FAIL);
    }


    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public String addUser(GspUser user) {
        try {
            if (!checkUser(user)) {
                return Response.fail(ErrorEnum.PARAM_EMPTY);
            }
            return userService.addUser(user) ? Response.suc(ErrorEnum.SUC) : Response.fail(ErrorEnum.OPERATION_ERROR);
        } catch (Exception e) {
            log.error("add user error", e);
        }
        return Response.fail(ErrorEnum.FAIL);
    }


    @RequestMapping(value = "/queryOne", method = RequestMethod.GET)
    public String queryOne(String id) {
        try {
            if (StringUtils.isNotBlank(id)) {
                GspUser user = userService.selectOne(id);
                return Response.suc(MoreObjects.firstNonNull(user, new GspUser()));
            }
        } catch (Exception e) {
            log.error("query one error", e);
        }
        return Response.fail(ErrorEnum.FAIL);
    }


    private boolean checkUser(GspUser user) {
        return user == null
                || 0 == user.getId()
                || StringUtils.isBlank(user.getName());

    }

}
