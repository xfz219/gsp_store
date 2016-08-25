package com.puhui.app.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.puhui.app.dao.ChangeMobileDao;
import com.puhui.app.vo.AppUserTokenChangeRecordVo;

/**
 * @comment 门店销售人员手机设备变更Manager
 * @author liwang
 * @time 2015年8月3日 下午3:31:42
 */
@Component
public class ChangeMobileManager{

	@Autowired
	private ChangeMobileDao changeMobileDao;
	
	/**
	 * @comment 门店销售人员手机设备变更数据库操作
	 * @returned void
	 * @author liwang
	 * @time 2015年8月3日 下午3:43:41
	 * @param vo
	 * @throws Exception
	 */
	public void doChangeMobile(String salesNo)throws Exception{
		this.changeMobileDao.deleteAppUserToken(salesNo);
	}

	
}
