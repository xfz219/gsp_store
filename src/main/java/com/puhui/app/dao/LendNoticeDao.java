package com.puhui.app.dao;

import com.puhui.app.po.LendNotice;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LendNoticeDao {

    @SuppressWarnings("unchecked")
    List<LendNotice> qryNotcieList(LendNotice vo);

    long qryNotciecount(LendNotice vo);

    void saveOrUpdate(LendNotice lendNotice);

    void delete(Long id);

    LendNotice queryById(Long id);
}
