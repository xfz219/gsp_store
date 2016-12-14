package com.puhui.app.service;

import com.puhui.app.po.AppLendNotice;
import com.puhui.app.search.AppLendNoticeSearch;
import com.puhui.app.vo.ReturnEntity;

import java.util.Map;

public interface LendNoticeService {

    Map<String, Object> qryLendNoticeList(AppLendNoticeSearch appLendNoticeSearch);

    void updateOrSaveLendNotice(AppLendNotice appLendNotice, String flag);

    ReturnEntity deleteLendNotice(Long id);

    AppLendNotice getLendNoticeById(Long id);

    ReturnEntity isuseLendNotice(Long id);
}
