package com.puhui.app.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.common.utils.Assert;
import com.puhui.app.service.AppLendAdvertisementVoService;
import com.puhui.app.vo.AppLendAdvertisementVo;
import com.puhui.app.vo.ReturnEntity;
/**
 * 
 * @author lcy
 *
 */
@Controller
@RequestMapping(value="/lendAdvertisement")
public class AppLendAdvertisementController extends BaseController {
	@InitBinder   
    public void initBinder(WebDataBinder binder) {   
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");   
        dateFormat.setLenient(true);   
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
    }
	
	private static Logger logger = LoggerFactory.getLogger(AppLendAdvertisementController.class);

	@Autowired
	private AppLendAdvertisementVoService appLendAdvertisementVoService;
	
	/**
	 * 页面跳转
	 * @author lichunyue
	 * @return
	 */
	@RequestMapping("/queryAdvertisement")
	public String queryAdvertisement(){
		return "business/queryAdvertisement";
	}
	/**
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping(value="/queryLendAdvertisementList")
	public Map<String, Object> queryLendAdvertisementList(AppLendAdvertisementVo vo){
		return appLendAdvertisementVoService.queryLendAdvertisementList(vo);
	}

	//保存上传的图片
	@RequestMapping(value="/saveLendAdvertisementFile")
	@ResponseBody
	public Object saveLendAdvertisementFile(AppLendAdvertisementVo lendAdvertisement, @RequestParam(value = "Filedata") MultipartFile myfile, HttpServletRequest req) {
		Assert.notNull(myfile.isEmpty(), "上传文件不能为空");
		Long id = lendAdvertisement.getId();
		AppLendAdvertisementVo lendAd ;
		Long returnId;
		if(null != id && id > 0){
			//更新
			lendAd = appLendAdvertisementVoService.selectLendAdvertisementById(id);
			//删除图片
			String picUrl = lendAd.getPicAddressUrl();
			if(StringUtils.isNotBlank(picUrl)){
				File file = new File(picUrl);
	            if (file.exists()) {
	                file.delete();
	            }
			}
		}else{
			//添加
			lendAd = new AppLendAdvertisementVo();
		}
		try {
			BeanUtils.copyProperty(lendAd, "originalPicName", myfile.getOriginalFilename());
			BeanUtils.copyProperty(lendAd, "picSize", myfile.getSize());
			BeanUtils.copyProperty(lendAd, "createTime", new Date());
			returnId = appLendAdvertisementVoService.saveLendAdvertisementPic(lendAd, myfile, req);
		} catch (Exception e) {
			logger.error("上传图片失败", e);
			return new ReturnEntity(false, "上传图片失败");
		}
        
		return new ReturnEntity(true, "上传图片成功", returnId);
	}
	
	/**
	 * 
	 * @param id
	 * @param response
	 * @param request
	 */
	@ResponseBody
    @RequestMapping(value = "/previewForAjax")
    public void previewForAjax(@RequestParam(value = "id", defaultValue = "0") String id, HttpServletResponse response,
            HttpServletRequest request) {
        try {
        	AppLendAdvertisementVo lendAdvertisement = appLendAdvertisementVoService.selectLendAdvertisementById(Long.valueOf(id));
        	String picUrl = lendAdvertisement.getPicAddressUrl();
        	String path = picUrl.substring(picUrl.indexOf("upload") - 1);
            previewForFlow(response, request, path);
        } catch (Exception e) {
            logger.error("附件预览失败", e);
        }

    }

    /**
     * 附件预览
     * 
     * @param response
     * @param request
     * @param path
     */
    private void previewForFlow(HttpServletResponse response, HttpServletRequest request, String path) {
        String filestyle = path.substring(path.lastIndexOf(".") + 1);
        String contentType = null;
        try {
        	String lowerCase = filestyle.toLowerCase();
            if ("jpg".equals(lowerCase) || "png".equals(lowerCase)
                    || "gif".equals(lowerCase) || "jepg".equals(lowerCase)
                    || "bmp".equals(lowerCase)) {
                contentType = "image/" + lowerCase + ";charset=UTF-8";
                response.setContentType("application/" + lowerCase + ";charset=UTF-8");
            }

            request.setCharacterEncoding("UTF-8");
            BufferedInputStream bis;
            BufferedOutputStream bos;
            long fileLength = new File(path).length();
            response.setContentType(contentType);
            response.setHeader("Content-disposition", "inline;filename=abc." + lowerCase + "");
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(path));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bis.close();
            bos.close();
        } catch (Exception e) {
        	logger.error("附件预览失败", e);
        }
    }
	/**
	 * 新增
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/lendAdvertisementDetail/{id}")
	public String lendAdvertisementDetail(@PathVariable("id") Long id, Model model){
		AppLendAdvertisementVo lendAdvertisement;
		lendAdvertisement = appLendAdvertisementVoService.selectLendAdvertisementById(id);
		model.addAttribute("lendAdvertisement", lendAdvertisement);
		return "business/addLendAdvertisement";
	}
	/**
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/viewLendAdvertisement/{id}")
	public String viewLendAdvertisement(@PathVariable("id") Long id, Model model){
		AppLendAdvertisementVo lendAdvertisement = appLendAdvertisementVoService.selectLendAdvertisementById(id);
		model.addAttribute("lendAdvertisement", lendAdvertisement);
		return "business/viewLendAdvertisement";
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteLendAdvertisement")
	public Object deleteLendAdvertisement(Long id){
        Assert.notNull(id, "选择删除id不能为空！");
        ReturnEntity returnEntity = new ReturnEntity(true, "删除成功");
    	appLendAdvertisementVoService.deleteLendAdvertisementById(id);
        return returnEntity;
	}

	/**
	 * 启用
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/enableLendAdvertisement")
	public Object enableLendAdvertisement(@RequestParam(value="id") String id){
		try {
			appLendAdvertisementVoService.updateLendAdvertisementTRUE(Long.parseLong(id));
		} catch (Exception e) {
			logger.error("id为:"+id+"的广告位启用失败", e);;
			return new ReturnEntity(false, "启用广告位失败");
		}
		
		return new ReturnEntity(true, "启用广告位成功");
	}
	
	/**
	 * 禁用
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/disableLendAdvertisement")
	public Object disableLendAdvertisement(@RequestParam(value="id") String id){
		try {
			appLendAdvertisementVoService.updateLendAdvertisementFALSE(Long.parseLong(id));
		} catch (Exception e) {
			logger.error("id为:"+id+"的广告位禁用失败", e);;
			return new ReturnEntity(false, "禁用广告位失败");
		}
		return new ReturnEntity(true, "禁用广告位成功");
	}
	/**
	 * 排前一位
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/forwartLendAdvertisementSort")
	public Object forwartLendAdvertisementSort(@RequestParam(value="id") String id){
		AppLendAdvertisementVo lendAdvertisement = appLendAdvertisementVoService.selectLendAdvertisementById(Long.valueOf(id));
		if(lendAdvertisement.getSort() == 1){
			return new ReturnEntity(false, "排位提前一位失败,已经最前");
		}
		try {
			appLendAdvertisementVoService.updateLendAdvertisementFront(Long.parseLong(id));
		} catch (Exception e) {
			logger.error("id为:"+id+"的广告位排位提前一位失败", e);;
			return new ReturnEntity(false, "排位提前一位失败");
		}
		
		return new ReturnEntity(true, "排位提前一位成功");
	}
	
	/**
	 * 排后一位
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/laterLendAdvertisementSort")
	public Object laterLendAdvertisementSort(@RequestParam(value="id") String id){
			try {
				appLendAdvertisementVoService.updateLendAdvertisementBehind(Long.parseLong(id));
				return new ReturnEntity(true, "排位推后一位成功");
			} catch (Exception e) {
				logger.error("id为:"+id+"排位提前一位失败", e);;
				return new ReturnEntity(false, "排位推后一位失败");
			}
	}

	
}
