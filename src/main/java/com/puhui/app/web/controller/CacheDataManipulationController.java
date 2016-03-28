package com.puhui.app.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puhui.app.common.constant.Select;
import com.puhui.app.common.page.mybatis.Page;
import com.puhui.app.service.DataCachingOperationsService;

/**
 * @comment 缓存数据操作
 * @author lichunyue
 */
@Controller
@RequestMapping(value = "/dataCaching")
public class CacheDataManipulationController {
	
	@Autowired
	private DataCachingOperationsService dataCachingOperationsService;
	
	
	/**
	 * 页面跳转
	 * @author lichunyue
	 * @return
	 */
	@RequestMapping("/goDataCachingOperations")
	public String goAccessoryResetMethod(){
		return "business/dataCachingOperations";
	}
	
	/**
	 * @comment 查询缓存表数据
	 * @author lichunyue
	 * @return
	 */
	@RequestMapping(value = "/selectDataMethod")
	@ResponseBody
	public Object selectAccessory(@RequestParam Map<String, Object> pageMap, 
    		@RequestParam(value = "className", required = false) String className,
    		@RequestParam(value = "attribution", required = false) String attribution,
    		@RequestParam(value = "codeName", required = false) String codeName){
    	Map<String, Object> objMap = new HashMap<String, Object>();
    	try{
    		int pageNo = Integer.valueOf(pageMap.get("page").toString());// 当前页
    		int pageSize= Integer.valueOf(pageMap.get("rows").toString());// 当前页大小
            Page page = Page.getPage(pageNo,pageSize);
            //查询条件参数
            Map<String, Object> paramMap = new HashMap<String, Object>(); 
        	paramMap.put("page", page);
        	paramMap.put("className", className);
        	paramMap.put("attribution", attribution);
        	paramMap.put("codeName", codeName);
        	
        	List<Map<String, Object>> list = dataCachingOperationsService.getDataServer(paramMap);
        	
        	objMap.put("total", page.getTotalCount());
        	objMap.put("rows", list);
    	}catch(Exception e){
    		System.out.println("查询附件失败");
    		throw new IllegalArgumentException(e);
    	}
    	return objMap;
	}
	
	/**
	 * @comment 缓存表数据修改
	 * @author lichunyue
	 */
	@RequestMapping(value = "/upDateDataMethod")
	@ResponseBody
	public void upDateDataMethod(@RequestParam(value = "className", required = false) String className,
    		@RequestParam(value = "attribution", required = false) String attribution,
    		@RequestParam(value = "codeName", required = false) String codeName,
    		@RequestParam(value = "codeValue", required = false) String codeValue,
    		@RequestParam(value = "meaning", required = false) String meaning,
    		@RequestParam(value = "message", required = false) String message,
    		@RequestParam(value = "id", required = false) String id){
		Map<String, Object> updateMap = new HashMap<String, Object>(); 
    	try{
    		updateMap.put("className", className);
    		updateMap.put("attribution", attribution);
    		updateMap.put("codeName", codeName);
    		updateMap.put("codeValue", codeValue);
    		updateMap.put("meaning", meaning);
    		updateMap.put("message", message);
    		updateMap.put("id", id);
    		dataCachingOperationsService.updateDataServer(updateMap);
    	}catch(Exception e){
    		System.out.println("更新失败");
    		throw new IllegalArgumentException(e);
    	}
	}
	
	/**
	 * @comment 缓存表数据新增
	 * @author lichunyue
	 */
	@RequestMapping(value = "/addDataMethod")
	@ResponseBody
	public void addDataMethod(@RequestParam(value = "className", required = false) String className,
    		@RequestParam(value = "attribution", required = false) String attribution,
    		@RequestParam(value = "codeName", required = false) String codeName,
    		@RequestParam(value = "codeValue", required = false) String codeValue,
    		@RequestParam(value = "meaning", required = false) String meaning,
    		@RequestParam(value = "message", required = false) String message){
		Map<String, Object> addMap = new HashMap<String, Object>(); 
    	try{
    		addMap.put("className", className);
    		addMap.put("attribution", attribution);
    		addMap.put("codeName", codeName);
    		addMap.put("codeValue", codeValue);
    		addMap.put("meaning", meaning);
    		addMap.put("message", message);
    		dataCachingOperationsService.addDataServer(addMap);
    	}catch(Exception e){
    		System.out.println("新增失败");
    		throw new IllegalArgumentException(e);
    	}
	}
}
