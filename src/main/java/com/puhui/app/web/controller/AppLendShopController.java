package com.puhui.app.web.controller;

import com.puhui.app.dao.AppLendShopDao;
import com.puhui.app.po.AppLendShop;
import com.puhui.app.po.AppPrizesSecret;
import com.puhui.app.po.AppWeixinArticle;
import com.puhui.app.service.AppLendShopService;
import com.puhui.app.service.PrizesService;
import com.puhui.app.utils.ReadExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Controller
@RequestMapping("/shop")
public class AppLendShopController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AppLendShopController.class);

    @Autowired
    private AppLendShopService appLendShopService;
    @Autowired
    private AppLendShopDao appLendShopDao;


    /**
     * 新增奖品
     * @return
     */
    @RequestMapping(value = "/add")
    public String add() {
        return "shop/add";
    }

    /**
     * 获取奖品
     * @return
     */
    @RequestMapping(value = "/get")
    public String get() {
        return "shop/get";
    }

    /**
     * 获取列表
     * @param als
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(AppLendShop als) {
        return appLendShopService.findList(als);
    }

    /**
     * 更新
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/update/{id}")
    public String update(@PathVariable(value = "id") Long id, ModelMap map) {
        String url = "shop/update";
        AppLendShop appLendShop = appLendShopDao.getAppLendShop(id);
        map.addAttribute("appLendShop", appLendShop);
        return url;
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
            return appLendShopService.addList(map, listMap);
        } catch (Exception e) {
            logger.error("添加失败，", e);
            m.put("result", "添加失败!");
            return m;
        }
    }


}
