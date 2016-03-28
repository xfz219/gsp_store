package com.puhui.app.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @comment map数据
 * @author liwang
 * @time 2015年8月3日 下午1:33:45
 */
public class Select {
	private static Select instance;
	
	private Select(){};
	
	public static Select getInstance(){
		if (instance == null) {    
			synchronized (Select.class) {    
				if (instance == null) {    
					instance = new Select();   
				}    
			}    
		}
		return instance;
	}
	
	/**
	 * @comment 客户端类型
	 * @returned Map<Object,String>
	 * @author liwang
	 * @time 2015年8月3日 下午1:33:33
	 * @return
	 */
	public Map<Object, String> clientTypeMap(){
		
		Map<Object, String> clientTypeMap = new HashMap<Object, String>();
		
		clientTypeMap.put(1, "网站");
		clientTypeMap.put(2, "苹果");
		clientTypeMap.put(3, "安卓");
		clientTypeMap.put(4, "其他");
		return clientTypeMap;
	}
	public Map<Object, String> checkTypeMap(){
		
		Map<Object, String> checkTypeMap = new HashMap<Object, String>();
		
		checkTypeMap.put(1, "征信报告");
		checkTypeMap.put(2, "银行流水");
		checkTypeMap.put(3, "淘宝验证");
		checkTypeMap.put(4, "手机运营商");
		return checkTypeMap;
	}
	public Map<String, Object> checkType(){
		
		Map<String, Object> checkType = new HashMap<String, Object>();
		
		checkType.put("征信报告", 1);
		checkType.put("银行流水", 2);
		checkType.put("淘宝验证", 3);
		checkType.put("手机运营商", 4);
		return checkType;
	}
	
	
}
