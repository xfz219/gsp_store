package com.puhui.app.dao;

import java.io.Serializable;

/**
 * 通用dao接口
 * @author xiaobowen
 *
 * @param <T>
 */
public interface BaseDao<T> extends Serializable {
    /**
     * 查询记录
     * @author xiaobowen
     * @param id
     * @return
     */
    public T query(Long id);
}
