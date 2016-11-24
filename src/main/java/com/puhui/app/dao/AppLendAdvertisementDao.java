package com.puhui.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.puhui.app.vo.AppLendAdvertisementVo;

/**
 * 广告位 
 * @author lcy
 *
 */
public interface AppLendAdvertisementDao extends BaseDao<Object>{

	/**
	 * 
	 * @param appLendAdvertisementVo
	 * @return
	 */
	public List<Map<String,Object>> queryLendAdvertisementList(AppLendAdvertisementVo appLendAdvertisementVo);

	/**
	 * @param id
	 * @return
	 */
	public AppLendAdvertisementVo selectLendAdvertisementById(@Param("id") long id);
	
	/**
	 * 禁用
	 * @param id
	 * @return
	 */
	public void updateLendAdvertisementFALSE(@Param("id") long id);
	
	/**
	 * 启用
	 * @param id
	 * @return
	 */
	public void updateLendAdvertisementTRUE(@Param("id") long id);
	
	/**
	 * @param appLendAdvertisementVo
	 * @return
	 */
	public AppLendAdvertisementVo addLendAdvertisement(AppLendAdvertisementVo appLendAdvertisementVo);

	/**
	 * 删除
	 * @param id
	 */
	public void deleteLendAdvertisementById(@Param("id") long id);

	/**
	 * 排前一位
	 * @param id
	 */
	public void updateLendAdvertisementFront(@Param("id") long id);

	/**
	 * 排后一位
	 * @param id
	 */
	public void updateLendAdvertisementBehind(@Param("id") long id);
}
