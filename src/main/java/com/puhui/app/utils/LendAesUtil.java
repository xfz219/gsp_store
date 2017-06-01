package com.puhui.app.utils;

import com.puhui.aes.AesEncryptionUtil;
import com.puhui.aes.AesEnum;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 加密解密工具类
 * @author  yangzhiqiang
 *
 */
public class LendAesUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(LendAesUtil.class);

	/**
	 * 加密
	 * 加密方法返回值会有“xy”前缀
	 * @param content
	 * @return
	 */
	public static String encrypt(String content){
		if(StringUtils.isEmpty(content)){
			return content;
		}
		if(content.startsWith("xy") && content.endsWith(AesEnum.FIRST.getDate())){
			return content;
		}
		try {
			content =  AesEncryptionUtil.encrypt(content);
		} catch (Exception e) {
			LOGGER.error("加密失败：", e);
		}
		return content;
	}

	/**
	 * 解密
	 * 解密方法需要传入带有“xy”前缀的字符串，才能解密；如果没有前缀则不会处理，直接返回原始字符串
	 * @param content
	 * @return
	 */
	public static String decrypt(String content){
		if(StringUtils.isEmpty(content)){
			return content;
		}
		if(!content.startsWith("xy") || !content.endsWith(AesEnum.FIRST.getDate())){
			return content;
		}
		try {
			content =  AesEncryptionUtil.decrypt(content);
		} catch (Exception e) {
			LOGGER.error("解密失败：", e);
		}
		return content;
	}
	
}
