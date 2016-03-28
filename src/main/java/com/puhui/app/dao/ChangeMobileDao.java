package com.puhui.app.dao;

import java.util.List;
import java.util.Map;

import com.puhui.app.vo.AppUserTokenChangeRecordVo;

/**
 * @comment 门店销售人员手机设备变更DAO
 * @author liwang
 * @time 2015年8月3日 上午10:58:24
 */
public interface ChangeMobileDao extends BaseDao<AppUserTokenChangeRecordVo>{

	/**
	 * @comment 查询销售人员手机设备变更记录
	 * @returned List<AppUserTokenChangeRecordVo>
	 * @author liwang
	 * @time 2015年8月3日 上午11:02:51
	 * @param paramMap 查询条件
	 * @return
	 * @throws Exception
	 */
	public List<AppUserTokenChangeRecordVo> queryAppUserTokenChangeRecord(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * @comment 查询app_user_token信息
	 * @returned List<AppUserTokenChangeRecordVo>
	 * @author liwang
	 * @time 2015年8月3日 下午3:26:01
	 * @param salesNo 员工编号
	 * @return
	 * @throws Exception
	 */
	public List<AppUserTokenChangeRecordVo> queryAppUserToken(String salesNo)throws Exception;
	
	/**
	 * @comment 创建数据
	 * @returned void
	 * @author liwang
	 * @time 2015年8月3日 下午3:41:54
	 * @param vo
	 * @throws Exception
	 */
	public void createAppUserTokenChangeRecord(AppUserTokenChangeRecordVo vo)throws Exception;
	
	/**
	 * @comment 删除app_user_token表中数据
	 * @returned void
	 * @author liwang
	 * @time 2015年8月3日 下午3:42:57
	 * @param id 主键
	 * @throws Exception
	 */
	public void deleteAppUserToken(Long id)throws Exception;
}
