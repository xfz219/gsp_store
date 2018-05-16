package com.puhui.app.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puhui.app.service.StaffService;
import com.puhui.app.service.SwaggerService;
import com.puhui.app.utils.CommonUtils;
import com.puhui.app.vo.EasyuiTreeNode;
import com.puhui.app.vo.NumberUtil;
import com.puhui.lend.vo.userCenter.UCRbacPermissionVo;
import com.puhui.nosql.redis.JedisTemplate;
import com.puhui.uc.vo.RemoteStaffVo;

/**
 * ' 主页控制类
 * 
 * @author xiaobowen
 */
@Controller
@RequestMapping
public class LoginController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

//    @Autowired
//    private JedisTemplate jedisTemplate;
    @Autowired
    private StaffService staffService;
    @Autowired
    private SwaggerService swaggerService;
    /**
     * 主页
     * 
     * @author xiaobowen
     * @return
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "main";
    }
    
    /**
     * 修改当前用户自己的密码
     * 
     * @param pwd
     * @param oldPwd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editCurrentStaffPwd", method = RequestMethod.POST)
    public Object editCurrentStaffPwd(@RequestParam("pwd") String pwd, @RequestParam("oldPwd") String oldPwd) {
        return this.staffService.changePwd(pwd, oldPwd);
    }

    /**
     * 登出
     * 
     * @author xiaobowen
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Subject currStaff = SecurityUtils.getSubject();
        if (currStaff != null) {
            // TODO 添加登出日志

            RemoteStaffVo staff = (RemoteStaffVo) currStaff.getPrincipal();
            logger.info(staff.getUsername() + " - 登出 - " + new Date());
            // 登出
            currStaff.logout();
//            jedisTemplate.del("staff-" + String.valueOf(staff.getId()));
        }
        
        Configuration config = null;
        try {
            config = new PropertiesConfiguration("application.properties");
            String casServerLogoutUrl = config.getString("cas_server_logout_url");
            String appReportLoginRedirect = config.getString("app_report_login_redirect");
            // 退出登录UC
            response.sendRedirect(casServerLogoutUrl + "?service=" + appReportLoginRedirect);
        } catch (Exception e) {
            logger.error("读取系统配置文件失败", e);
        }
    }

    /**
     * 生成左侧菜单树
     * 
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/treeView")
    public String treeView(HttpServletRequest request) {
    try{
	  Subject currStaff = SecurityUtils.getSubject();
	  String id = request.getParameter("id");
	  List<EasyuiTreeNode> nodeList = new ArrayList<EasyuiTreeNode>();
      EasyuiTreeNode treeNode;
      UCRbacPermissionVo per;
      String path = request.getContextPath();
      Comparator<EasyuiTreeNode> comparator = new Comparator<EasyuiTreeNode>() {
          @Override
          public int compare(EasyuiTreeNode o1, EasyuiTreeNode o2) {
              EasyuiTreeNode p1 = o1;
              EasyuiTreeNode p2 = o2;
              if (p1.getSort() == null || p2.getSort() == null) {
                  return Integer.parseInt(p1.getId()) - Integer.parseInt(p2.getId());
              } else {
                  if (NumberUtil.doubleSubtract(p1.getSort(), p2.getSort()) > 0) {
                      return 1;
                  } else {
                      return -1;
                  }
              }
          }
      };
      if (StringUtils.isBlank(id)) { // id为空，查询所有的一级菜单
    	if (currStaff != null) {
    		RemoteStaffVo staff = CommonUtils.getLoginStaff();
    		List<UCRbacPermissionVo> list = swaggerService.top(staff.getId(),path);
    		for (int i = 0; i < list.size(); i++) {
              per = list.get(i);
              if (per.getPermissionType().equals("FUNCTION")) {// 是功能
                  treeNode = new EasyuiTreeNode(per.getId() + "", staff.getId().toString(), per.getName(), per.getIconCls(),per.getSort());
                  Map<String, String> map = new HashMap<String, String>();
                  map.put("url", per.getUrl());
                  treeNode.setAttributes(map);
                  nodeList.add(treeNode);
              } else {// 是菜单
                  treeNode = new EasyuiTreeNode(per.getId() + "", staff.getId().toString(), per.getName(), "closed", per.getIconCls(),
                          per.getSort());
                  nodeList.add(treeNode);
              }
          }
    		}
    	Collections.sort(nodeList, comparator);
      }else{
    	// 查询出当前选中的权限
  		RemoteStaffVo staff = CommonUtils.getLoginStaff();
  		List<UCRbacPermissionVo> list =  swaggerService.sub(Long.parseLong(id),staff.getId());
  		for (int i = 0; i < list.size(); i++) {
            per = list.get(i);
            if (per.getPermissionType().equals("FUNCTION")) {// 是功能
                treeNode = new EasyuiTreeNode(per.getId() + "", staff.getId().toString(), per.getName(), per.getIconCls(),per.getSort());
                Map<String, String> map = new HashMap<>();
                map.put("url", per.getUrl());
                treeNode.setAttributes(map);
                nodeList.add(treeNode);
            } else {// 是菜单
                treeNode = new EasyuiTreeNode(per.getId() + "", staff.getId().toString(), per.getName(), "closed", per.getIconCls(),
                        per.getSort());
                nodeList.add(treeNode);
            }
        }
      }
      // 排序，因为从set里取出来的值是无序的
      Collections.sort(nodeList, comparator);
      return new ObjectMapper().writeValueAsString(nodeList);
    } catch (Exception e) {
        e.printStackTrace();
        return "";
    }
}
}
