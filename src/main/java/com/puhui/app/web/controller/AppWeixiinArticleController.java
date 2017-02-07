package com.puhui.app.web.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puhui.app.po.AppWeixinArticle;
import com.puhui.app.search.AppLendNoticeSearch;
import com.puhui.app.service.AppWeixiinArticleService;
import com.puhui.app.service.SwaggerService;
import com.puhui.app.vo.ReturnEntity;
import com.puhui.uc.vo.RemoteOrganizationVo;

@Controller
@RequestMapping("/AppWeixiinArticle")
public class AppWeixiinArticleController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(AppWeixiinArticleController.class);
    @Autowired
    private AppWeixiinArticleService appWeixiinArticleService;

    @Autowired
    private SwaggerService swaggerService;

    /**
     * 查询公告列表
     * 
     * @param appLendNoticeSearch 搜索条件
     * @return 公告列表
     */
    @ResponseBody
    @RequestMapping(value = "/qryArticleList")
    public Map<String, Object> qryArticleList(AppLendNoticeSearch appLendNoticeSearch) {
        return appWeixiinArticleService.qryLendNoticeList(appLendNoticeSearch);
    }

    @RequestMapping("/index")
    public String goAccessoryResetMethod(){
        return "weixin/queryLendNotice";
    }

    @RequestMapping("/toAdd")
    public String toAddLendNotice(){
        return "weixin/addLendNotice";
    }

    /**
     * 新建公告
     * 
     * @param appLendNotice
     * @return
     */
    @RequestMapping(value = "/addLendNotice")
    @ResponseBody
    public Map<String, Object> addLendNotice(AppWeixinArticle appWeixiinArticle) {
        Map<String, Object> map = new HashMap<>();
        try {
        	appWeixiinArticleService.updateOrSaveLendNotice(appWeixiinArticle, "add");
            map.put("status", "success");
            map.put("result", "添加公告成功!");
        } catch (Exception e) {
        	log.error("添加公告失败", e);
            map.clear();
            map.put("status", "false");
            map.put("result", "添加公告失败!");
        }
        return map;
    }

    /**
     * 删除文章
     * 
     * @return
     */
    @RequestMapping(value = "/deleteLendNotice")
    @ResponseBody
    public Object deleteLendNotice(String id) {
    	ReturnEntity returnEntity;
        Assert.notNull(id, "选择删除id不能为空！");
        returnEntity = appWeixiinArticleService.deleteLendNotice(Long.valueOf(id));
        return returnEntity;
    }

    @RequestMapping(value = "/getLendNoticeById/{id}/{flag}")
    public String getLendNoticeById(@PathVariable(value = "id") Long id, @PathVariable(value = "flag") String flag,
            ModelMap map) {
        String url = "weixin/editLendNotice";
        if (Objects.equals("look",flag)) {
            url = "weixin/lookLendNotice";
        }
        AppWeixinArticle appWeixiinArticle = appWeixiinArticleService.getLendNoticeById(id);
        map.addAttribute("appWeixiinArticle", appWeixiinArticle);
        return url;
    }

    /**
     * 编辑公告
     * 
     * @param appLendNotice
     * @return
     */
    @RequestMapping(value = "/editLendNotice")
    @ResponseBody
    public Map<String, Object> editLendNotice(AppWeixinArticle appWeixiinArticle) {
        Map<String, Object> map = new HashMap<>();
        try {
        	appWeixiinArticleService.updateOrSaveLendNotice(appWeixiinArticle, "edit");
            map.put("status", "success");
            map.put("result", "编辑公告成功!");
        } catch (Exception e) {
            log.error("编辑系统公告失败", e);
            map.clear();
            map.put("status", "false");
            map.put("result", "编辑公告失败!");
        }
        return map;
    }

    
    /**
     * 发布公告
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/isuseLendNotice")
    @ResponseBody
    public Object isuseLendNotice(String id) {
        Assert.notNull(id, "选择发布id不能为空！");
        ReturnEntity returnEntity;
		log.info("开始发布系统公告id为:{}的系统公告", id);
		returnEntity = appWeixiinArticleService.isuseLendNotice(Long.valueOf(id));
        return returnEntity;
    }

    private String getShopCodeByIDs(String noticeDepartment) {
        List<Long> list;
        if(StringUtils.isNotBlank(noticeDepartment)){
            String[] strArr = StringUtils.split(noticeDepartment,",");
            Long[] arr = (Long[]) ConvertUtils.convert(strArr, Long.class);
            list = Arrays.asList(arr);
            StringBuilder stringBuilder = new StringBuilder();
            list.forEach(id -> {
                RemoteOrganizationVo remoteOrganizationVo = swaggerService.orgId(id);
                if (remoteOrganizationVo.getOrganizationType().equals("SHOP")) {
                    stringBuilder.append(remoteOrganizationVo.getCode()).append(",");
                }
            });
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            return stringBuilder.toString();
        }
        return StringUtils.EMPTY;
    }


    private String getShopIdByCodes(String codes) {
        List<String> list;
        StringBuilder stringBuilder = new StringBuilder();
        if(StringUtils.isNotBlank(codes)){
            String[] strArr = StringUtils.split(codes,",");
            list = Arrays.asList(strArr);
            list.forEach(code -> {
                RemoteOrganizationVo remoteOrganizationVo = swaggerService.orgCode(code);
                stringBuilder.append(remoteOrganizationVo.getId()).append(",");
            });
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            return stringBuilder.toString();
        }
        return StringUtils.EMPTY;
    }

}
