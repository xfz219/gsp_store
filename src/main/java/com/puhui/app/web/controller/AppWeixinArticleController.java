package com.puhui.app.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.puhui.app.po.AppWeixinArticle;
import com.puhui.app.search.AppLendNoticeSearch;
import com.puhui.app.service.AppWeixinArticleService;
import com.puhui.app.service.SwaggerService;
import com.puhui.app.vo.ReturnEntity;
import com.puhui.uc.vo.RemoteOrganizationVo;

@Controller
@RequestMapping("/AppWeixiinArticle")
public class AppWeixinArticleController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(AppWeixinArticleController.class);

    @Autowired
    private AppWeixinArticleService appWeixinArticleService;

    @Autowired
    private SwaggerService swaggerService;

    /**
     * 查询公告列表
     * 
     * @param appLendNoticeSearch 搜索条件
     * @return 公告列表
     */
    @ResponseBody
    @RequestMapping(value = "/qryArticleList")
    public Map<String, Object> qryArticleList(AppLendNoticeSearch appLendNoticeSearch) {
        return appWeixinArticleService.qryLendNoticeList(appLendNoticeSearch);
    }

    @RequestMapping("/index")
    public String goAccessoryResetMethod(){
        return "weixin/queryLendNotice";
    }

    @RequestMapping("/toAdd")
    public String toAddLendNotice(){
        return "weixin/addLendNotice";
    }

    /**
     * 新建公告
     * 
     * @param appLendNotice
     * @return
     */
    @RequestMapping(value = "/addLendNotice")
    @ResponseBody
    public Map<String, Object> addLendNotice(AppWeixinArticle appWeixiinArticle) {
        Map<String, Object> map = new HashMap<>();
        try {
      		if(null != appWeixiinArticle.getId() && appWeixiinArticle.getId() > 0){
      			appWeixinArticleService.updateOrSaveLendNotice(appWeixiinArticle, "edit");
      		}else{
      			map.put("status", "false");
                map.put("result", "上传图片不可为空！");
                return map;
      		}
            map.put("status", "success");
            map.put("result", "添加文章成功!");
        } catch (Exception e) {
        	log.error("添加文章失败", e);
            map.clear();
            map.put("status", "false");
            map.put("result", "添加文章失败!");
        }
        return map;
    }
    
    
    //保存上传的图片
  	@RequestMapping(value="/saveLendAdvertisementFile")
  	@ResponseBody
  	public Object saveLendAdvertisementFile(AppWeixinArticle appWeixinArticle, @RequestParam(value = "Filedata") MultipartFile myfile, HttpServletRequest req) {
  		Assert.notNull(myfile.isEmpty(), "上传文件不能为空");
  		Long id = appWeixinArticle.getId();
  		AppWeixinArticle lendAd;
  		Long returnId;
  		if(null != id && id > 0){
  			//更新
  			lendAd = appWeixinArticleService.getLendNoticeById(id);
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
  			lendAd = new AppWeixinArticle();
  		}
  		try {
  			BeanUtils.copyProperty(lendAd, "originalPicName", myfile.getOriginalFilename());
  			returnId = appWeixinArticleService.saveLendAdvertisementPic(lendAd, myfile, req);
  		} catch (Exception e) {
  			log.error("上传图片失败", e);
  			return new ReturnEntity(false, "上传图片失败");
  		}
  		return new ReturnEntity(true, "上传图片成功", returnId);
  	}
  	
    /**
     * 删除文章
     * 
     * @return
     */
    @RequestMapping(value = "/deleteLendNotice")
    @ResponseBody
    public Object deleteLendNotice(String id) {
    	ReturnEntity returnEntity;
        Assert.notNull(id, "选择删除id不能为空！");
        returnEntity = appWeixinArticleService.deleteLendNotice(Long.valueOf(id));
        return returnEntity;
    }

    @RequestMapping(value = "/getLendNoticeById/{id}/{flag}")
    public String getLendNoticeById(@PathVariable(value = "id") Long id, @PathVariable(value = "flag") String flag,
            ModelMap map) {
        String url = "weixin/editLendNotice";
        if (Objects.equals("look",flag)) {
            url = "weixin/lookLendNotice";
        }
        AppWeixinArticle appWeixiinArticle = appWeixinArticleService.getLendNoticeById(id);
        map.addAttribute("appWeixiinArticle", appWeixiinArticle);
        return url;
    }

    /**
     * 编辑公告
     * 
     * @param appLendNotice
     * @return
     */
    @RequestMapping(value = "/editLendNotice")
    @ResponseBody
    public Map<String, Object> editLendNotice(AppWeixinArticle appWeixiinArticle) {
        Map<String, Object> map = new HashMap<>();
        try {
        	appWeixinArticleService.updateOrSaveLendNotice(appWeixiinArticle, "edit");
            map.put("status", "success");
            map.put("result", "编辑公告成功!");
        } catch (Exception e) {
            log.error("编辑系统公告失败", e);
            map.clear();
            map.put("status", "false");
            map.put("result", "编辑公告失败!");
        }
        return map;
    }

    
    /**
     * 发布公告
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/isuseLendNotice")
    @ResponseBody
    public Object isuseLendNotice(String id) {
        Assert.notNull(id, "选择发布id不能为空！");
        ReturnEntity returnEntity;
		log.info("开始发布系统公告id为:{}的系统公告", id);
		returnEntity = appWeixinArticleService.isuseLendNotice(Long.valueOf(id));
        return returnEntity;
    }

    private String getShopCodeByIDs(String noticeDepartment) {
        List<Long> list;
        if(StringUtils.isNotBlank(noticeDepartment)){
            String[] strArr = StringUtils.split(noticeDepartment,",");
            Long[] arr = (Long[]) ConvertUtils.convert(strArr, Long.class);
            list = Arrays.asList(arr);
            StringBuilder stringBuilder = new StringBuilder();
            list.forEach(id -> {
                RemoteOrganizationVo remoteOrganizationVo = swaggerService.orgId(id);
                if (remoteOrganizationVo.getOrganizationType().equals("SHOP")) {
                    stringBuilder.append(remoteOrganizationVo.getCode()).append(",");
                }
            });
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            return stringBuilder.toString();
        }
        return StringUtils.EMPTY;
    }


    private String getShopIdByCodes(String codes) {
        List<String> list;
        StringBuilder stringBuilder = new StringBuilder();
        if(StringUtils.isNotBlank(codes)){
            String[] strArr = StringUtils.split(codes,",");
            list = Arrays.asList(strArr);
            list.forEach(code -> {
                RemoteOrganizationVo remoteOrganizationVo = swaggerService.orgCode(code);
                stringBuilder.append(remoteOrganizationVo.getId()).append(",");
            });
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            return stringBuilder.toString();
        }
        return StringUtils.EMPTY;
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
        	AppWeixinArticle appWeixinArticle = appWeixinArticleService.getLendNoticeById(Long.valueOf(id));
        	String picUrl = appWeixinArticle.getPicAddressUrl();
        	String path = picUrl.substring(picUrl.indexOf("upload") - 1);
            previewForFlow(response, request, path);
        } catch (Exception e) {
            log.error("附件预览失败", e);
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
        	log.error("附件预览失败", e);
        }
    }

}
