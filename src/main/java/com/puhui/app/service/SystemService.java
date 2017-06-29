package com.puhui.app.service;


import com.puhui.app.vo.AppPushMessageVo;

import java.util.List;
import java.util.Map;

public interface SystemService {

	List<Map<String, Object>> findList();

	boolean update(Map<String, Object> params);

}
