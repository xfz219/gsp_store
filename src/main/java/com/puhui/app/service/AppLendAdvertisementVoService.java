package com.puhui.app.service;

import java.util.Map;

import com.puhui.app.vo.AppLendAdvertisementVo;

/**
 * 广告位
 * @author lcy
 *
 */
public interface AppLendAdvertisementVoService {

	/**
	 * 查询显示
	 * @param appLendAdvertisementVo
	 * @return
	 */
	public Map<String, Object> queryLendAdvertisementList(AppLendAdvertisementVo appLendAdvertisementVo);
	/**
	 * 查询图片
	 * @param id
	 * @return
	 */
	public AppLendAdvertisementVo selectLendAdvertisementById(Long id);
	/**
	 * @param lendAd
	 * @return
	 */
	public AppLendAdvertisementVo getLendAdvertisementByIdentityAndStatus(AppLendAdvertisementVo lendAd);
	
}
