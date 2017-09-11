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

    /**
     * 查询奖品数量
     * @param apn
     * @return
     */
    int getAppPrizesNumber(AppPrizesNumber apn);

    /**
     * 更新奖品数量
     * @param apn
     * @return
     */
    void updateAppPrizesNumber(AppPrizesNumber apn);
}
