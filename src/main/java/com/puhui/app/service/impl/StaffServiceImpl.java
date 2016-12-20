package com.puhui.app.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puhui.app.service.StaffService;
import com.puhui.app.service.SwaggerService;
import com.puhui.app.utils.CommonUtils;
import com.puhui.app.vo.ReturnEntity;
import com.puhui.uc.vo.RemoteStaffVo;

@Service
public class StaffServiceImpl implements StaffService{
    private static final Logger logger = LoggerFactory.getLogger(StaffServiceImpl.class);

    @Autowired
    private SwaggerService swaggerService;
    
    @Override
    public ReturnEntity<Object> changePwd(String pwd, String oldPwd) {
        ReturnEntity<Object> returnEntity;
        RemoteStaffVo currStaff = CommonUtils.getLoginStaff();
        // 如果当前登录用户为空,这直接返回
        if (currStaff == null) {
            returnEntity = new ReturnEntity<Object>(false,"当前用户没有登录,请重新登录");
        }else{
            try {
                logger.info("-----------修改用户密码开始。登录用户：{}---------------",currStaff.getUsername());
                this.swaggerService.changePwd(currStaff.getId(), oldPwd, pwd);
                returnEntity = new ReturnEntity<Object>(true,"修改密码成功！");
            } catch (Exception e) {
                logger.error(e.toString(),e);
                logger.info("-----------修改用户密码错误。登录用户：{}。原因：{}---------------",currStaff.getUsername(),e.getMessage());
                returnEntity = new ReturnEntity<Object>(false,e.getMessage());
            }
            logger.info("-----------修改用户密码结束。登录用户：{}---------------",currStaff.getUsername());
        }
        return returnEntity;
    }

}
