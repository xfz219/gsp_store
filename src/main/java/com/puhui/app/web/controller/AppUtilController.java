package com.puhui.app.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puhui.app.service.LendUcService;
import com.puhui.app.utils.Staff;
import com.puhui.uc.vo.RemoteStaffVo;


@Controller
@RequestMapping("/appUtil")
public class AppUtilController extends BaseController{
	
	@Autowired
    private LendUcService lendUcService;
	
    /**
     * 获取机构树
     * 
     * @author yangzhiqiang
     * @date 2015年10月20日 下午2:31:40
     */
    @ResponseBody
    @RequestMapping(value = "/getOrgTree")
    public Object getOrgTree() {
        Subject currStaff = SecurityUtils.getSubject();
        RemoteStaffVo staff = (RemoteStaffVo) currStaff.getPrincipal(); 
        if (staff != null) {
            return lendUcService.getOrgTree(staff.getOrganizationVo().getCode());
        } else {
            return null;
        }
	}

}
