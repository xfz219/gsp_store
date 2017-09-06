package com.puhui.app.dao;


import com.puhui.app.enums.PrizeType;
import com.puhui.app.po.AppPrizesSecret;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

}
