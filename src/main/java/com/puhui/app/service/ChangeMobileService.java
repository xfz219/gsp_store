package com.puhui.app.service;

import java.util.List;
import java.util.Map;

import com.puhui.app.po.AppUserTokenChangeRecord;
import com.puhui.app.vo.AppUserTokenChangeRecordVo;

/**
 * @comment 销售人员设备变更SERVICE 
 * @author liwang
 * @time 2015年8月3日 上午11:12:28
 */
public interface ChangeMobileService {

	/**
	 * @comment 查询销售人员设备变更记录
	 * @returned List<AppUserTokenChangeRecord>
	 * @author liwang
	 * @time 2015年8月3日 上午11:13:56
	 * @param paramMap 查询条件
	 * @return
	 * @throws Exception
	 */
	public List<AppUserTokenChangeRecord> queryAppUserTokenChangeRecord(Map<String, Object> paramMap)throws Exception;
	
	/**
	 * @comment 通过员工编号查询激活状态的app_user_token表数据
	 * @returned AppUserTokenChangeRecordVo
	 * @author liwang
	 * @time 2015年8月3日 下午3:16:08
	 * @param salesNo 销售编号
	 * @return
	 * @throws Exception
	 */
	public  List<AppUserTokenChangeRecordVo> queryAppUserToken(String salesNo)throws Exception;

	/**
	 * @comment 销售人员变更手机设备
	 * @returned void
	 * @author liwang
	 * @time 2015年8月3日 下午3:14:18
	 * @param list 
	 * @throws Exception
	 */
	public void changeMobile(String salesNo)throws Exception;
}
