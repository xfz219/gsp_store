package com.puhui.app.common.constant;

/**
 * @comment 常量类
 * @author liwang
 * @time 2015年7月20日 下午6:42:10
 */
public class Constant {
	public static final int productUpdateCount = 3;//产品总的可变更次数
	
	public static final int creditReport = 1;//征信报告
	public static final int payFlow = 2;//工资流水
	public static final int taobaoAccount = 3;//淘宝身份
	public static final int communicationInfo = 4;//运营商身份
	public static final int personalInfo = 5;//身份信息类型
	public static final int loanDemand = 6;//借款申请
	public static final int occupationInfo = 7;//职业信息
	public static final int contactInfo = 8;//联系人信息
	public static final int idPhoto = 9;//身份证照片
	public static final int residenceCertificate = 10;//居住证明
	public static final int workCertificate = 11;//工作证明
	public static final int operatorCertificate = 12;//经营证明
	public static final int estateCertificate = 13;//房产证证明
	public static final int businessAddressCertificate = 14;//经营地址
	public static final int other = 15;//其他证明
	public static final int married = 16;//已婚证明
	public static final int children = 17;//子女证明
	public static final int providentSocial = 18;//社保/公积金
	public static final int degree = 19;//学历证明
	
	
	
	public static final String REDIS_KEY_VALUE = "value";//审核状态缓存key
	public static String REDIS_KEY_CODE_MEAN = "code_mean";//审核状态缓存key
	public static String REDIS_KEY_VALUE_MEAN = "value_mean";//审核状态缓存key
}
