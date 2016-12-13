package com.puhui.app.service;

import com.puhui.app.po.LendNotice;
import com.puhui.app.vo.ReturnEntity;

import java.util.Map;

public interface LendNoticeService {

    Map<String, Object> qryLendNoticeList(LendNotice vo);

    void updateOrSaveLendNotice(LendNotice lendNotice, String flag);

    ReturnEntity deleteLendNotice(Long id);

    LendNotice getLendNoticeById(Long id);

    ReturnEntity isuseLendNotice(Long id);
}
