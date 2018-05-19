package com.gsp.app.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.gsp.app.model.User;
import com.gsp.app.serives.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by finup on 2018/5/19.
 */
@Controller
@RequestMapping("/home/")
public class MyController {

    @Autowired
    private MyService myService;

    @RequestMapping("test")
    public String index() {
        return "my";
    }


    @RequestMapping("list")
    @ResponseBody
    public String userList() {
        List<User> userList = myService.userList();
        System.out.println(userList.size());
        return JSONObject.toJSONString(userList);
    }
}
