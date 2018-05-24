package com.gsp.app.web.controller;

import com.google.common.base.MoreObjects;
import com.gsp.app.dao.UserDao;
import com.gsp.app.model.GspUser;
import com.gsp.app.model.GspUserRole;
import com.gsp.app.model.ResponseVo;
import com.gsp.app.serives.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * fengzhix.xu on 2018/5/19.
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;


    @RequestMapping("/queryAll")
    public Object queryAllUser(GspUser pojo) {
        Map<String, Object> objMap = new HashMap<>();
        try {
            List<GspUser> gspUsers = userService.selectAllUser(pojo);
            objMap.put("total", 30);
            objMap.put("rows", gspUsers);
            return objMap;
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

    /**
     * 启用
     */
    @RequestMapping(value="/enable")
    @ResponseBody
    public Object enable(@RequestParam(value="id") String id){
        try {
            userService.updateEnabledById(Long.parseLong(id), true);
        } catch (Exception e) {
            return ResponseVo.ofErrorMessage();
        }
        return ResponseVo.ofSuccess();
    }

    /**
     * 禁用
     */
    @RequestMapping(value="/stop")
    @ResponseBody
    public Object stop(@RequestParam(value="id") String id) {
        try {
            userService.updateEnabledById(Long.parseLong(id), false);
        } catch (Exception e) {
            return ResponseVo.ofErrorMessage();
        }
        return ResponseVo.ofSuccess();
    }

    /**
     * 更新跳转
     */
    @RequestMapping(value = "/update/{id}")
    public String updateById(@PathVariable(value = "id") Long id, ModelMap map) {
        String url = "user/update";
        GspUser gspUser = userDao.findUserById(id);
        map.addAttribute("gspUser", gspUser);
        return url;
    }

    /**
     * 更新数据
     */
    @RequestMapping(value = "/updateGspUser")
    @ResponseBody
    public Object updateGspUser(GspUser user) {
        try {
            userDao.updateUser(user);
        } catch (Exception e) {
            return ResponseVo.ofErrorMessage();
        }
        return ResponseVo.ofSuccess();
    }

    /**
     * 新增跳转
     */
    @RequestMapping("add")
    public String user() {
        return "user/add";
    }

    /**
     * 增加用户信息
     */
    @RequestMapping(value = "/addGspUser")
    @ResponseBody
    public Object addGspUser(GspUser user) {
        try {
            userDao.addUser(user);
        } catch (Exception e) {
            return ResponseVo.ofErrorMessage();
        }
        return ResponseVo.ofSuccess();
    }

    /**
     * 查看权限跳转
     */
    @RequestMapping(value = "/userRole/{id}")
    public String userRole(@PathVariable(value = "id") Long id, ModelMap map) {
        String url = "user/userRole";
        GspUser gspUser = userDao.findUserById(id);
        map.addAttribute("gspUser", gspUser);
        return url;
    }

    /**
     * 查询用户对应角色
     */
    @RequestMapping("/queryUserRole")
    public Object queryUserRole(GspUserRole pojo) {
        Map<String, Object> objMap = new HashMap<>();
        try {
            List<GspUser> gspUsers = userService.selectAllUser(new GspUser());
            objMap.put("total", 30);
            objMap.put("rows", gspUsers);
            return objMap;
        } catch (Exception e) {
            log.error("query all error", e);
        }
        return ResponseVo.ofErrorMessage();
    }

}
