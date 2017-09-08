package com.puhui.app.dao;


import com.puhui.app.po.AppPrizesNumber;

/**
 * 奖品数量表
 */
public interface AppPrizesNumberDao extends BaseDao {

    /**
     * 插入
     * @param appPrizesNumber
     * @return
     */
    void addAppPrizesNumber(AppPrizesNumber appPrizesNumber);
}
