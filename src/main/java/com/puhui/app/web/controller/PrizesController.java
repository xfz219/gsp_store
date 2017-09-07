package com.puhui.app.web.controller;

import com.puhui.app.po.AppPrizesSecret;
import com.puhui.app.service.PrizesService;
import com.puhui.app.service.SystemService;
import com.puhui.app.vo.AppLendAdvertisementVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/prizes")
public class PrizesController extends BaseController {

    @Autowired
    private PrizesService prizesService;

    /**
     * 新增奖品
     * @return
     */
    @RequestMapping(value = "/add")
    public String add() {
        return "prizes/add";
    }

    /**
     * 获取奖品
     * @return
     */
    @RequestMapping(value = "/get")
    public String get() {
        return "prizes/get";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(AppPrizesSecret aps) {
        return prizesService.findList(aps);
    }

    @RequestMapping(value = "/addList")
    @ResponseBody
    public Object addList(@RequestParam Map<String,Object> map) {
        Map<String, Object> m = new HashMap<>();

        prizesService.addList(map);

        m.put("status", "success");
        m.put("result", "添加成功!");
        return m;
    }


}
