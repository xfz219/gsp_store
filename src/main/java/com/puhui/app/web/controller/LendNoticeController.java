package com.puhui.app.web.controller;

import com.puhui.app.po.LendNotice;
import com.puhui.app.service.LendNoticeService;
import com.puhui.app.service.LendUcService;
import com.puhui.app.vo.ReturnEntity;
import com.puhui.uc.api.service.RemoteOrganizationService;
import com.puhui.uc.api.service.RemoteUserCentreService;
import com.puhui.uc.vo.RemoteOrganizationVo;
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

import java.util.*;

@Controller
@RequestMapping("/LendNotice")
public class LendNoticeController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(LendNoticeController.class);
    @Autowired
    private LendNoticeService lendNoticeService;

    @Autowired
    private LendUcService lendUcService;
    @Autowired
    private RemoteUserCentreService lendCenterUcService;
    @Autowired
    private RemoteOrganizationService remoteOrganizationService;

    /**
     * 查询公告列表
     * 
     * @param vo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/qryNoticeList")
    public Map<String, Object> qryNoticeList(LendNotice vo) {
        return lendNoticeService.qryLendNoticeList(vo);
    }

    @RequestMapping("/index")
    public String goAccessoryResetMethod(){
        return "LendNotice/queryLendNotice";
    }

    /**
     * 新建公告
     * 
     * @param lendNotice
     * @return
     */
    @RequestMapping(value = "/addLendNotice")
    @ResponseBody
    public Map<String, Object> addLendNotice(LendNotice lendNotice) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
        	String noticeDepartment = lendNotice.getNoticeDepartment();
        	//将lendNotice中的noticeDepartment由id换成code（id所在的organization为门店时）
        	String codeStr = getShopCodeByIDs(noticeDepartment);
        	lendNotice.setNoticeDepartment(codeStr);
            lendNoticeService.updateOrSaveLendNotice(lendNotice, "add");
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
     * 删除公告
     * 
     * @return
     */
    @RequestMapping(value = "/deleteLendNotice")
    @ResponseBody
    public Object deleteLendNotice(String id) {
    	ReturnEntity returnEntity;
        Assert.notNull(id, "选择删除id不能为空！");
        returnEntity = lendNoticeService.deleteLendNotice(Long.valueOf(id));
        return returnEntity;
    }

    @RequestMapping(value = "/getLendNoticeById/{id}/{flag}")
    public String getLendNoticeById(@PathVariable(value = "id") Long id, @PathVariable(value = "flag") String flag,
            ModelMap map) {
        String url = "lend/editLendNotice";
        if (flag.equals("look")) {
            url = "lend/lookLendNotice";
        }
        LendNotice lendNotice = lendNoticeService.getLendNoticeById(id);
        String noticeDepartment = lendNotice.getNoticeDepartment();
    	//将lendNotice中的noticeDepartment由code换成id（id所在的organization为门店时）
        String idStr = getShopCodeByIDs(noticeDepartment);
    	lendNotice.setNoticeDepartment(idStr);
        map.addAttribute("lendNotice", lendNotice);
        return url;
    }

    /**
     * 编辑公告
     * 
     * @param lendNotice
     * @return
     */
    @RequestMapping(value = "/editLendNotice")
    @ResponseBody
    public Map<String, Object> editLendNotice(LendNotice lendNotice) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
        	String noticeDepartment = lendNotice.getNoticeDepartment();
        	//将lendNotice中的noticeDepartment由id换成code（id所在的organization为门店时）
        	String codeStr = getShopCodeByIDs(noticeDepartment);
        	lendNotice.setNoticeDepartment(codeStr);
            lendNoticeService.updateOrSaveLendNotice(lendNotice, "edit");
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
		returnEntity = lendNoticeService.isuseLendNotice(Long.valueOf(id));
        return returnEntity;
    }

    private String getShopCodeByIDs(String noticeDepartment) {
        List<Long> list;
        list = new ArrayList<Long>();
        if(StringUtils.isNotBlank(noticeDepartment)){
            String[] strArr = StringUtils.split(noticeDepartment,",");
            Long[] arr = (Long[]) ConvertUtils.convert(strArr, Long.class);
            list = Arrays.asList(arr);
            StringBuilder stringBuilder = new StringBuilder();
            list.forEach(id -> {
                RemoteOrganizationVo remoteOrganizationVo = remoteOrganizationService.queryById(id);
                if (remoteOrganizationVo.getOrganizationType().equals("SHOP")) {
                    stringBuilder.append(remoteOrganizationVo.getCode()).append(",");
                }
            });
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            return stringBuilder.toString();
        }
        return StringUtils.EMPTY;
    }

}
