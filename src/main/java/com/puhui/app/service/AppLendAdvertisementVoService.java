package com.puhui.app.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

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
	public AppLendAdvertisementVo selectLendAdvertisementById(long id);
	
	/**
	 * 
	 * @param lendAdvertisement
	 * @param myfile
	 * @param request
	 * @return
	 */
	public Long saveLendAdvertisementPic(AppLendAdvertisementVo lendAdvertisement, MultipartFile myfile, HttpServletRequest request);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public void deleteLendAdvertisementById(long id);

	/**
	 * 禁用
	 * @param id
	 */
	public void updateLendAdvertisementFALSE(long id);
	
	/**
	 * 启用
	 * @param id
	 */
	public void updateLendAdvertisementTRUE(long id);

	/**
	 * 提前一位
	 * @param id
	 */
	public void updateLendAdvertisementFront(long id);

	/**
	 * 排后一位
	 * @param id
	 */
	public void updateLendAdvertisementBehind(long id);
	
}
