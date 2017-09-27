package com.puhui.app.web.controller;

import com.puhui.app.dao.AppLendShopDao;
import com.puhui.app.po.AppLendShop;
import com.puhui.app.po.AppPrizesSecret;
import com.puhui.app.po.AppWeixinArticle;
import com.puhui.app.service.AppLendShopService;
import com.puhui.app.service.PrizesService;
import com.puhui.app.utils.ReadExcel;
import com.puhui.app.vo.ReturnEntity;
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
     * 新增门店
     * @return
     */
    @RequestMapping(value = "/add")
    public String add() {
        return "shop/add";
    }

    /**
     * 获取门店
     * @return
     */
    @RequestMapping(value = "/get")
    public String get() {
        return "shop/get";
    }

    /**
     * 更新跳转
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/update/{id}")
    public String updateById(@PathVariable(value = "id") Long id, ModelMap map) {
        String url = "shop/update";
        AppLendShop appLendShop = appLendShopDao.getAppLendShop(id);
        map.addAttribute("appLendShop", appLendShop);
        return url;
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
     * 更新数据
     * @param als
     * @return
     */
    @RequestMapping(value = "/updateAppLendShop")
    @ResponseBody
    public Object updateAppLendShop(AppLendShop als) {
        Map<String, Object> map = new HashMap<>();
        try{
            appLendShopService.updateAppLendShop(als);
            map.put("status", "success");
            map.put("result", "成功!");
            return map;
        }catch (Exception e){
            map.put("status", "false");
            map.put("result", "失败!");
            return map;
        }
    }

    /**
     * 增加门店
     * @param als
     * @return
     */
    @RequestMapping(value = "/addAppLendShop")
    @ResponseBody
    public Object addAppLendShop(AppLendShop als) {
        Map<String, Object> map = new HashMap<>();
        try {
            appLendShopService.addAppLendShop(als);
            map.put("status", "success");
            map.put("result", "成功!");
            return map;
        } catch (Exception e) {
            map.put("status", "false");
            map.put("result", "失败!");
            return map;
        }
    }

    /**
     * 启用
     * @param id
     * @return
     */
    @RequestMapping(value="/enable")
    public Object enable(@RequestParam(value="id") String id){
        try {
            appLendShopService.updateEnabledById(Long.parseLong(id), true);
        } catch (Exception e) {
            return new ReturnEntity(false, "启用门店失败");
        }
        return new ReturnEntity(true, "启用门店成功");
    }

    /**
     * 禁用
     * @param id
     * @return
     */
    @RequestMapping(value="/stop")
    public Object stop(@RequestParam(value="id") String id){
        try {
            appLendShopService.updateEnabledById(Long.parseLong(id), false);
        } catch (Exception e) {
            return new ReturnEntity(false, "禁用门店失败");
        }
        return new ReturnEntity(true, "禁用门店成功");
    }


}
