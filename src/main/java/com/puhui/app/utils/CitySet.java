package com.puhui.app.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.puhui.uc.vo.RemoteOrganizationVo;

public class CitySet {
	
	protected static final Set<String> allSet = new HashSet<>();
	static{
		allSet.add("乌鲁木齐");
		allSet.add("安阳");
		allSet.add("洛阳");
		allSet.add("南阳");
		allSet.add("平顶山");
		allSet.add("商丘");
		allSet.add("许昌");
		allSet.add("新乡");
		allSet.add("信阳");
		allSet.add("郑州");
		allSet.add("开封");
		allSet.add("周口");
		allSet.add("焦作");
		allSet.add("驻马店");
		allSet.add("百色");
		allSet.add("桂林");
		allSet.add("柳州");
		allSet.add("南宁");
		allSet.add("钦州");
		allSet.add("毕节");
		allSet.add("贵阳");
		allSet.add("六盘水");
		allSet.add("兴义");
		allSet.add("遵义");
		allSet.add("保定");
		allSet.add("石家庄");
		allSet.add("邢台");
		allSet.add("张家口");
		allSet.add("衡水");
		allSet.add("沧州");
		allSet.add("包头");
		allSet.add("赤峰");
		allSet.add("呼和浩特");
		allSet.add("巴彦淖尔");
		allSet.add("呼伦贝尔");
		allSet.add("乌兰察布");
		allSet.add("成都");
		allSet.add("达州");
		allSet.add("德阳");
		allSet.add("泸州");
		allSet.add("乐山");
		allSet.add("绵阳");
		allSet.add("南充");
		allSet.add("宜宾");
		allSet.add("攀枝花");
		allSet.add("常德");
		allSet.add("郴州");
		allSet.add("衡阳");
		allSet.add("岳阳");
		allSet.add("长沙");
		allSet.add("株洲");
		allSet.add("怀化");
		allSet.add("大理");
		allSet.add("红河");
		allSet.add("昆明");
		allSet.add("曲靖");
		allSet.add("玉溪");
		allSet.add("普洱");
		allSet.add("东莞");
		allSet.add("广州");
		allSet.add("惠州");
		allSet.add("河源");
		allSet.add("汕头");
		allSet.add("湛江");
		allSet.add("中山");
		allSet.add("肇庆");
		allSet.add("深圳");
		allSet.add("佛山");
		allSet.add("福州");
		allSet.add("泉州");
		allSet.add("厦门");
		allSet.add("漳州");
		allSet.add("赣州");
		allSet.add("九江");
		allSet.add("南昌");
		allSet.add("上饶");
		allSet.add("宜春");
		allSet.add("海口");
		allSet.add("黄石");
		allSet.add("武汉");
		allSet.add("襄阳");
		allSet.add("宜昌");
		allSet.add("十堰");
		allSet.add("哈尔滨");
		allSet.add("齐齐哈尔");
		allSet.add("牡丹江");
		allSet.add("佳木斯");
		allSet.add("大庆");
		allSet.add("济南");
		allSet.add("烟台");
		allSet.add("东营");
		allSet.add("潍坊");
		allSet.add("锦州");
		allSet.add("盘锦");
		allSet.add("沈阳");
		allSet.add("营口");
		allSet.add("大连");
		allSet.add("南通");
		allSet.add("苏州");
		allSet.add("无锡");
		allSet.add("长春");
		allSet.add("延吉");
		allSet.add("松原");
		allSet.add("四平");
		allSet.add("绍兴");
		allSet.add("嘉兴");
		allSet.add("宁波");
		allSet.add("太原");
		allSet.add("临汾");
		allSet.add("重庆");
		allSet.add("万州");
		allSet.add("西安");
		allSet.add("渭南");
		allSet.add("咸阳");
		allSet.add("榆林");
		allSet.add("银川");
		allSet.add("北京");
		allSet.add("天津");
		allSet.add("西宁");
		allSet.add("兰州");
		allSet.add("酒泉");
		allSet.add("芜湖");
		allSet.add("合肥");
		allSet.add("上海");
	}

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
					if (CitySet.allSet.contains(map.get("cityName"))) {
						cityList.add(map);
					}
				}
			return cityList;
		}
	
}
