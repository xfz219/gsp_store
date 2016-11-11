package com.puhui.app.dao;

import java.util.List;
import java.util.Map;

import com.puhui.app.vo.AppLendAdvertisementVo;

/**
 * 
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
	public AppLendAdvertisementVo selectLendAdvertisementById(Long id);
}
