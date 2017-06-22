package com.puhui.app.web.controller;

import com.puhui.app.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {

    @Autowired
    private SystemService systemService;

    /**
     * 系统升级首页
     * @return
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "system/index";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list() {
        return systemService.findList();
    }

    /**
     * 更改
     */
    @ResponseBody
    @RequestMapping(value = "/update")
    public boolean update(@RequestParam Map<String, Object> params) {
       return systemService.update(params);
    }

}
