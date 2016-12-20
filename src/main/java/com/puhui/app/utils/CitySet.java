package com.puhui.app.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.puhui.uc.vo.RemoteOrganizationVo;

public class CitySet {
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	public static List<Map<String,Object>> getCityMap(List<RemoteOrganizationVo> list) {
		List<Map<String,Object>> cityList = new ArrayList<> ();
		for(RemoteOrganizationVo ro : list){
				Map<String, Object> map = new HashMap<>();
				map.put("cityName", ro.getName());
				map.put("cityCode", ro.getCode());
				map.put("id", ro.getId());
				cityList.add(map);
			}
		return cityList;
	}
	
}
