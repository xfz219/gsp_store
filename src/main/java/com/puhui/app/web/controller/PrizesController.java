package com.puhui.app.web.controller;

import com.puhui.app.po.AppPrizesSecret;
import com.puhui.app.service.PrizesService;
import com.puhui.app.utils.ReadExcel;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Controller
@RequestMapping("/prizes")
public class PrizesController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(PrizesController.class);

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


    /**
     * 增加奖品
     * @param map
     * @return
     */
    @RequestMapping(value = "/addList")
    @ResponseBody
    public Object addList(@RequestParam Map<String,String> map, @RequestParam(value = "file") MultipartFile myfile) {
        Map<String, Object> m = new HashMap<>();
        try {
            if (Objects.equals(map.get("fileName"), "")) {
                m.put("result", "上传文件不可为空!");
                return m;
            }
            List<Map<String, String>> listMap = ReadExcel.readExcel(myfile.getInputStream());
            return prizesService.addList(map, listMap);
        } catch (Exception e) {
            logger.error("添加失败，", e);
            m.put("result", "添加失败!");
            return m;
        }
    }


}
