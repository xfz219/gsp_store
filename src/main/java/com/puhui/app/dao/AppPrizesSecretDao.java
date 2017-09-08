package com.puhui.app.dao;


import com.puhui.app.po.AppPrizesSecret;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 奖品秘密
 */
public interface AppPrizesSecretDao extends BaseDao {


	/**
	 * 查询奖品秘密列表
	 * @param prizeType
	 * @param use
	 * @return
	 */
	List<AppPrizesSecret> findList(@Param("prizeType") String prizeType, @Param("use") Boolean use);


	/**
	 * 插入
	 * @param appPrizesSecretList
	 * @return
	 */
	void addAppPrizesSecret(List<AppPrizesSecret> appPrizesSecretList);

}
