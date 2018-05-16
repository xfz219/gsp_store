package com.puhui.app.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puhui.app.common.constant.Constant;
import com.puhui.app.dao.AppDataCachingOperationsDao;
import com.puhui.app.service.AppPushService;
import com.puhui.app.service.DataCachingOperationsService;
import com.puhui.app.vo.AppPushMessageVo;
import com.puhui.nosql.redis.JedisTemplate;

@Service
public class DataCachingOperationsServiceImpl implements DataCachingOperationsService{
	
	@Autowired
	private AppDataCachingOperationsDao appDataCachingOperationsDao;
//	@Autowired
//	private JedisTemplate jedisTemplate;
	/**
	 * @comment 查询缓存表数据
	 * @author lichunyue
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getDataServer(Map<String, Object> paramMap)throws Exception{
		List<Map<String, Object>> list = appDataCachingOperationsDao.getDataServer(paramMap);
		List<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
		for(Map<String, Object> map : list){
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("id", map.get("id"));
			map1.put("className", map.get("className"));
			map1.put("attribution", map.get("attribution"));
			map1.put("codeName", map.get("codeName"));
			map1.put("codeValue", map.get("codeValue"));
			map1.put("meaning", map.get("meaning"));
			map1.put("message", map.get("message"));
			arrayList.add(map1);
		}
		return arrayList;
		 
	}
	/**
	 * @comment 缓存表数据修改
	 * @author lichunyue
	 */
	@Override
	public void updateDataServer(Map<String, Object> updateMap)throws Exception {
		appDataCachingOperationsDao.updateDataServer(updateMap);
//		jedisTemplate.hset(Constant.REDIS_KEY_VALUE+"_"+updateMap.get("className").toString()+"_"+updateMap.get("attribution").toString(),updateMap.get("codeName").toString(), updateMap.get("codeValue").toString());
//        jedisTemplate.hset(Constant.REDIS_KEY_CODE_MEAN+"_"+updateMap.get("className").toString()+"_"+updateMap.get("attribution").toString(),updateMap.get("codeName").toString(), updateMap.get("meaning").toString());
//        jedisTemplate.hset(Constant.REDIS_KEY_VALUE_MEAN+"_"+updateMap.get("className").toString()+"_"+updateMap.get("attribution").toString(),updateMap.get("codeValue").toString(), updateMap.get("meaning").toString());
	}
	/**
	 * @comment 缓存表数据新增
	 * @author lichunyue
	 */
	@Override
	public void addDataServer(Map<String, Object> addMap) throws Exception {
		appDataCachingOperationsDao.addDataServer(addMap);
//		jedisTemplate.hset(Constant.REDIS_KEY_VALUE+"_"+addMap.get("className").toString()+"_"+addMap.get("attribution").toString(),addMap.get("codeName").toString(), addMap.get("codeValue").toString());
//        jedisTemplate.hset(Constant.REDIS_KEY_CODE_MEAN+"_"+addMap.get("className").toString()+"_"+addMap.get("attribution").toString(),addMap.get("codeName").toString(), addMap.get("meaning").toString());
//        jedisTemplate.hset(Constant.REDIS_KEY_VALUE_MEAN+"_"+addMap.get("className").toString()+"_"+addMap.get("attribution").toString(),addMap.get("codeValue").toString(), addMap.get("meaning").toString());
	}
}
