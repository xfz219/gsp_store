package com.puhui.app.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.puhui.app.dao.AppLendAdvertisementDao;
import com.puhui.app.service.AppLendAdvertisementVoService;
import com.puhui.app.utils.FileUtil;
import com.puhui.app.vo.AppLendAdvertisementVo;
/**
 * 
 * @author lcy
 *
 */
@Service
public class AppLendAdvertisementVoServiceImpl implements AppLendAdvertisementVoService{
	
	private static final Logger logger = LoggerFactory.getLogger(AppLendAdvertisementVoServiceImpl.class);
	public final static String ATTACHMENT_PATH = new StringBuffer().append(File.separator).append("upload").append(File.separator).append("puhui-lend").append(File.separator).append("board")
			.append(File.separator).append("pic").append(File.separator).toString();

	@Autowired
	private AppLendAdvertisementDao appLendAdvertisementDao;

	@Override
	public Map<String, Object> queryLendAdvertisementList(AppLendAdvertisementVo appLendAdvertisementVo) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> list = appLendAdvertisementDao.queryLendAdvertisementList(appLendAdvertisementVo);
        map.put("rows", list);
		return map;
	}
	
	@Override
	public AppLendAdvertisementVo selectLendAdvertisementById(long id) {
		return appLendAdvertisementDao.selectLendAdvertisementById(id);
	}

	
	@Override
	public Long saveLendAdvertisementPic(AppLendAdvertisementVo lendAdvertisement, MultipartFile myfile, HttpServletRequest request){
        String savePath = null;
		try {
			savePath = this.getPath(lendAdvertisement, myfile, request);
		} catch (Exception e) {
			logger.error("广告位保存异常：",e);
		} 
        lendAdvertisement.setPicAddressUrl(savePath);
        String repStr = new StringBuffer().append(File.separator).append("upload").append(File.separator).append("puhui-lend").toString();
        String picAccessUrl = savePath.replace(repStr, "");
        lendAdvertisement.setPicAccessUrl(picAccessUrl);
        lendAdvertisement.setSort(1);
    	//添加
    	appLendAdvertisementDao.addLendAdvertisement(lendAdvertisement);
        return lendAdvertisement.getId();
	}
	
	/**
     * @throws IOException
     * @throws IllegalStateException
     * @Title: getPath
     * @Description: 获取广告位图片上传路径
     * @param @param lendAdvertisement
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    private String getPath(AppLendAdvertisementVo lendAdvertisement, MultipartFile myfile, HttpServletRequest request) throws IllegalStateException,
            IOException {
        String filePath = new StringBuffer(60).append(ATTACHMENT_PATH)
                .append(DateFormatUtils.format(new Date(), "yyyy-MM-dd")).append(File.separator)
                .append(DateFormatUtils.format(new Date(), "HH-mm-ss")).toString();
        if (!FileUtil.isExists(filePath)) {
            FileUtil.mkdirs(filePath);
        }
        String fileExtention = FileUtil.getExtension(lendAdvertisement.getOriginalPicName());
        String uuid = UUID.randomUUID().toString();
        File file = new File(filePath, new StringBuffer(30).append(uuid).append(".").append(fileExtention).toString());
        myfile.transferTo(file);
        String basePath = new StringBuffer(180).append(request.getScheme()).append(":").append(File.separator).append(File.separator).append(request.getServerName()).append(":").
        		append(request.getServerPort()).append(filePath).append(File.separator).append(uuid).append(".").append(fileExtention).toString();
        return basePath;
    }

    /**
     * 删除
     * @param id
     */
	@Override
	public void deleteLendAdvertisementById(long id) {
		//删除
    	appLendAdvertisementDao.deleteLendAdvertisementById(id);
	}

	/**
	 * 禁用
	 * @param id
	 */
	@Override
	public void updateLendAdvertisementFALSE(long id) {
		appLendAdvertisementDao.updateLendAdvertisementFALSE(id);
	}
	
	/**
	 * 启用
	 * @param id
	 */
	@Override
	public void updateLendAdvertisementTRUE(long id) {
		appLendAdvertisementDao.updateLendAdvertisementTRUE(id);
	}

	/**
	 * 提前一位
	 * @param id
	 */
	@Override
	public void updateLendAdvertisementFront(long id) {
		appLendAdvertisementDao.updateLendAdvertisementFront(id);
	}

	/**
	 * 提后一位
	 * @param id
	 */
	@Override
	public void updateLendAdvertisementBehind(long id) {
		appLendAdvertisementDao.updateLendAdvertisementBehind(id);
	}

	/**
	 * 更新公告内容
	 * @param lendAd
	 */
	@Override
	public void updateLendAdvertisement(AppLendAdvertisementVo lendAd) {
		appLendAdvertisementDao.updateLendAdvertisement(lendAd);
	}
	
}
