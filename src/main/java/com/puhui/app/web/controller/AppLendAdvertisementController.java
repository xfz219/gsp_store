package com.puhui.app.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
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

import com.puhui.app.service.AppLendAdvertisementVoService;
import com.puhui.app.vo.AppLendAdvertisementVo;

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

//	//保存上传的图片
//	@RequestMapping(value="/saveLendAdvertisementFile")
//	@ResponseBody
//	public Object saveLendAdvertisementFile(LendAdvertisement lendAdvertisement, @RequestParam(value = "Filedata") MultipartFile myfile, HttpServletRequest req) {
//		Assert.notNull(myfile.isEmpty(), "上传文件不能为空");
//		Long id = lendAdvertisement.getId();
//		LendAdvertisement lendAd = null;
//		Long returnId;
//		if(null != id && id > 0){
//			//更新
//			lendAd = lendAdvertisementService.selectLendAdvertisementById(id);
//			//删除图片
//			String picUrl = lendAd.getPicAddressUrl();
//			if(StringUtils.isNotBlank(picUrl)){
//				File file = new File(picUrl);
//	            if (file.exists()) {
//	                file.delete();
//	            }
//			}
//		}else{
//			//添加
//			lendAd = new LendAdvertisement();
//		}
//		try {
//			BeanUtils.copyProperty(lendAd, "originalPicName", myfile.getOriginalFilename());
//			BeanUtils.copyProperty(lendAd, "picSize", myfile.getSize());
//			BeanUtils.copyProperty(lendAd, "createTime", new Date());
//			returnId = lendAdvertisementService.saveLendAdvertisementPic(lendAd, myfile, req);
//		} catch (Exception e) {
//			logger.error("上传图片失败", e);
//			return new ReturnEntity(false, "上传图片失败");
//		}
//        
//		return new ReturnEntity(true, "上传图片成功", returnId);
//	}
//	
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
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/lendAdvertisementDetail/{id}")
	public String lendAdvertisementDetail(@PathVariable("id") Long id, Model model){
		AppLendAdvertisementVo lendAdvertisement;
		lendAdvertisement = appLendAdvertisementVoService.selectLendAdvertisementById(id);
		model.addAttribute("lendAdvertisement", lendAdvertisement);
		if(null != id && id > 0){
			return "business/editLendAdvertisement";
		}else{
			return "business/addLendAdvertisement";
		}
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
	 * 
	 * @param lendAdvertisement
	 * @return
	 */
	@RequestMapping(value="/saveLendAdvertisement")
	@ResponseBody
	public Map<String, Object> saveLendAdvertisement(AppLendAdvertisementVo lendAdvertisement){
		Map<String, Object> map = new HashMap<>();
		Long id = lendAdvertisement.getId();
		if(null != id && id > 0){
			try {
				AppLendAdvertisementVo lendAd = appLendAdvertisementVoService.selectLendAdvertisementById(id);
				BeanUtils.copyProperty(lendAd, "name", lendAdvertisement.getName());
				BeanUtils.copyProperty(lendAd, "url", lendAdvertisement.getUrl());
				BeanUtils.copyProperty(lendAd, "enabled", lendAdvertisement.getEnabled());
				//原来用户类型的广告位排位大于更改用户类型的广告位的排位时，原来的广告位排位大于更改用户类型的广告位排位的广告为的排位都减1
				AppLendAdvertisementVo av = appLendAdvertisementVoService.getLendAdvertisementByIdentityAndStatus(lendAd);
				for(LendAdvertisement lendAd1 : list){
					if(lendAd1.getSort() > lendAd.getSort()){
						lendAd1.setSort(lendAd1.getSort() - 1);
						lendAdvertisementService.updateLendAdvertisement(lendAd1);
					}
				}
				
				BeanUtils.copyProperty(lendAd, "customerIdentity", lendAdvertisement.getCustomerIdentity());
				BeanUtils.copyProperty(lendAd, "customerLendStatus", lendAdvertisement.getCustomerLendStatus());
				//如果用户类型改变，排位要变
			    int maxSort = lendAdvertisementService.getMaxSortByIdentityAndStatus(lendAdvertisement);
				int sort = maxSort >= 1 ? (maxSort+1):1;
				BeanUtils.copyProperty(lendAd, "sort", sort);
				lendAdvertisementService.updateLendAdvertisement(lendAd);
				map.put("status", "success");
	            map.put("result", "更新广告位成功!");
			} catch (Exception e) {
				logger.error("更新广告位失败", e);
				map.put("status", "false");
	            map.put("result", "更新广告位失败!");
			}
		}else{
			map.put("status", "false");
            map.put("result", "图片没有上传，添加广告位失败!");
		}
		return map;
	}
	
//	@RequestMapping(value="/deleteLendAdvertisement")
//	public Object deleteLendAdvertisement(Long id){
//        Assert.notNull(id, "选择删除id不能为空！");
//        ReturnEntity returnEntity = new ReturnEntity(true, "删除成功");
//        try {
//        	returnEntity = lendAdvertisementService.deleteLendAdvertisementById(id);
//        } catch (Exception e) {
//			StringBuffer sb = new StringBuffer();
//			sb.append("id为：");
//			sb.append(id);
//			sb.append("广告位添加失败");
//            returnEntity = new ReturnEntity(false, sb.toString());
//        }
//        return returnEntity;
//	}
//	
//	@RequestMapping(value="/enableLendAdvertisement")
//	public Object enableLendAdvertisement(@RequestParam(value="id") String id){
//		LendAdvertisement lendAdvertisement = lendAdvertisementService.selectLendAdvertisementById(Long.valueOf(id));
//		
//		int count = lendAdvertisementService.selectCountByIdentityAndStatus(lendAdvertisement);
//		if(count>=5){
//			return new ReturnEntity(false, "该类型广告位已经启用的数量已经达到5个!");
//		}
//		
//		try {
//			lendAdvertisement.setEnabled("1");
//			lendAdvertisementService.updateLendAdvertisement(lendAdvertisement);
//		} catch (Exception e) {
//			logger.error("id为:"+id+"的广告位启用失败", e);;
//			return new ReturnEntity(false, "启用广告位失败");
//		}
//		
//		return new ReturnEntity(true, "启用广告位成功");
//	}
//	
//	@RequestMapping(value="/disableLendAdvertisement")
//	public Object disableLendAdvertisement(@RequestParam(value="id") String id){
//		LendAdvertisement lendAdvertisement = lendAdvertisementService.selectLendAdvertisementById(Long.valueOf(id));
//		try {
//			lendAdvertisement.setEnabled("0");
//			lendAdvertisementService.updateLendAdvertisement(lendAdvertisement);
//		} catch (Exception e) {
//			logger.error("id为:"+id+"的广告位禁用失败", e);;
//			return new ReturnEntity(false, "禁用广告位失败");
//		}
//		
//		return new ReturnEntity(true, "禁用广告位成功");
//	}
//	
//	@RequestMapping(value="/forwartLendAdvertisementSort")
//	public Object forwartLendAdvertisementSort(@RequestParam(value="id") String id){
//		LendAdvertisement lendAdvertisement = lendAdvertisementService.selectLendAdvertisementById(Long.valueOf(id));
//		int sort = lendAdvertisement.getSort() - 1;
//		
//		List<LendAdvertisement> list = lendAdvertisementService.getLendAdvertisementByIdentityAndStatus(lendAdvertisement);
//		try {
//			for(LendAdvertisement lendAd : list){
//				if(lendAd.getSort() == sort){
//					lendAd.setSort(lendAd.getSort() + 1);
//					lendAdvertisementService.updateLendAdvertisement(lendAd);
//				}
//			}
//			lendAdvertisement.setSort(lendAdvertisement.getSort() - 1);
//			lendAdvertisementService.updateLendAdvertisement(lendAdvertisement);
//		} catch (Exception e) {
//			logger.error("id为:"+id+"的广告位排位提前一位失败", e);;
//			return new ReturnEntity(false, "排位提前一位失败");
//		}
//		
//		return new ReturnEntity(true, "排位提前一位成功");
//	}
//	
//	@RequestMapping(value="/laterLendAdvertisementSort")
//	public Object laterLendAdvertisementSort(@RequestParam(value="id") String id){
//		ReturnEntity returnEntity = new ReturnEntity(true, "排位提前一位成功");
//		LendAdvertisement lendAdvertisement = lendAdvertisementService.selectLendAdvertisementById(Long.valueOf(id));
//		int maxSort = lendAdvertisementService.getMaxSortByIdentityAndStatus(lendAdvertisement);
//		if(maxSort == lendAdvertisement.getSort()){
//			returnEntity = new ReturnEntity(false, "该广告位的排位已是该类型客户的广告位的最大排位，不能再推后一位");
//		}else{
//			int sort = lendAdvertisement.getSort() + 1;
//			List<LendAdvertisement> list = lendAdvertisementService.getLendAdvertisementByIdentityAndStatus(lendAdvertisement);
//			try {
//				for(LendAdvertisement lendAd : list){
//					if(lendAd.getSort() == sort){
//						lendAd.setSort(lendAd.getSort() - 1);
//						lendAdvertisementService.updateLendAdvertisement(lendAd);
//					}
//				}
//				lendAdvertisement.setSort(lendAdvertisement.getSort() + 1);
//				lendAdvertisementService.updateLendAdvertisement(lendAdvertisement);
//				returnEntity = new ReturnEntity(true, "排位推后一位成功");
//			} catch (Exception e) {
//				logger.error("id为:"+id+"排位提前一位失败", e);;
//				returnEntity = new ReturnEntity(false, "排位推后一位失败");
//			}
//		}
//		
//		return returnEntity;
//	}

	
}
