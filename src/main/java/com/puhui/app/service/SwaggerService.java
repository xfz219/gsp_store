package com.puhui.app.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.puhui.app.utils.HttpUtils;
import com.puhui.lend.vo.LendRequestVo;
import com.puhui.lend.vo.ResponseVo;
import com.puhui.uc.vo.DataGrid;
import com.puhui.uc.vo.RemoteOrganizationVo;
import com.puhui.uc.vo.RemoteStaffVo;

/**
 * @author lcy
 */
@Service
public class SwaggerService {
	private static final Logger logger = LoggerFactory.getLogger(SwaggerService.class);
	private final String SUCCEED = "200";//成功

	@Autowired
	private OauthService oauthService;
	/**
	 * 获客个贷系统数据
	 * @param appLendRequestId
	 * @return
	 */
	public Map<String,Object> getLendRequestIdMethod(Long appLendRequestId) {
		String url = HttpUtils.PUHUI_LEND_URL_ID;
		ResponseVo<LendRequestVo> responseIdVo = oauthService.jsonGet(url, ResponseVo.class,
				new Object[] { appLendRequestId });
		if (responseIdVo.getCode().equals(SUCCEED)) {
			Map<String,Object> linkedHashMap = (Map) responseIdVo.getResult();
			return linkedHashMap;
		} else {
			return null;
		}
	}
	
	/**
	 * 登陆认证
	 * @param salesNo
	 * @param password
	 * @return
	 */
	public boolean login(String salesNo,String password) {
		boolean succeed = false;
		String url = HttpUtils.PUHUI_LEND_URL_LOGIN;
		ResponseVo<String> responseVo = oauthService.jsonPost(url+"?employeeNo="+salesNo+"&password="+password, null, ResponseVo.class);
		if(responseVo.getCode().equals(SUCCEED)){
			succeed = true;
		}
		return succeed;
	}
	
	/**
	 * 根据员工编号获取UC中用户信息
	 * @param salesNo
	 * @return
	 */
	public RemoteStaffVo employeeNo(String salesNo) {
		RemoteStaffVo remoteStaffVo = null;
		String url = HttpUtils.PUHUI_LEND_URL_EMPLOYEENO;
		ResponseVo<RemoteStaffVo> responseVo = oauthService.jsonGet(url, new ParameterizedTypeReference<ResponseVo<RemoteStaffVo>>(){}, new Object[] {salesNo});
		if(responseVo.getCode().equals(SUCCEED)){
			remoteStaffVo = (RemoteStaffVo) responseVo.getResult();
		}
		return remoteStaffVo;
	}
	
	/**
	 * 根据ID获取UC中用户信息
	 * @param id
	 * @return
	 */
	public RemoteStaffVo ucId(Long id) {
		RemoteStaffVo remoteStaffVo = null;
		String url = HttpUtils.PUHUI_LEND_URL_UCID;
		ResponseVo<RemoteStaffVo> responseVo = oauthService.jsonGet(url, new ParameterizedTypeReference<ResponseVo<RemoteStaffVo>>(){}, new Object[] {id});
		if(responseVo.getCode().equals(SUCCEED)){
			remoteStaffVo = (RemoteStaffVo) responseVo.getResult();
		}
		return remoteStaffVo;
	}
	
	/**
	 * 根据用户ID修改用户密码
	 * @param staffId
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	public boolean changePwd(Long staffId,String oldPassword,String newPassword) {
		String url = HttpUtils.PUHUI_LEND_URL_CHANGEPWD;
		try {
			oauthService.jsonPut(url+"?oldPassword="+oldPassword+"&newPassword="+newPassword, null, new Object[] {staffId});
        } catch (Exception e) {
        	logger.info("门店修改用户密码错误,错误原因{}",e);
            return false;
        }
		return true;
	}
	
	/**
	 * 根据机构code模糊查询机构
	 * @param code
	 * @return
	 */
	public List<RemoteOrganizationVo> like(String code) {
		String url = HttpUtils.PUHUI_LEND_URL_LIKE;
		ResponseVo<List<RemoteOrganizationVo>> responseVo = oauthService.jsonGet(url, new ParameterizedTypeReference<ResponseVo<List<RemoteOrganizationVo>>>(){}, new Object[] {code});
		List<RemoteOrganizationVo> list = (List<RemoteOrganizationVo>) responseVo.getResult();
		return list;
	}
	
	/**
	 * 根据机构code模糊查询机构
	 * @param id
	 * @return
	 */
	public List<RemoteOrganizationVo> orgId(Long id) {
		String url = HttpUtils.PUHUI_LEND_URL_ORGID;
		ResponseVo<List<RemoteOrganizationVo>> responseVo = oauthService.jsonGet(url, new ParameterizedTypeReference<ResponseVo<List<RemoteOrganizationVo>>>(){}, new Object[] {id});
		List<RemoteOrganizationVo> list = (List<RemoteOrganizationVo>) responseVo.getResult();
		return list;
	}
	
	/**
	 * 根据用户信息查询用户分页列表
	 * @param id
	 * @return
	 */
	public List<RemoteStaffVo> ucPage(Integer page,Integer rows,RemoteStaffVo remoteStaffVo) {
		String url = HttpUtils.PUHUI_LEND_URL_UCPAGE;
		ResponseVo<DataGrid<RemoteStaffVo>> responseVo = oauthService.jsonPost(url+"?page="+page+"&rows="+rows, remoteStaffVo , new ParameterizedTypeReference<ResponseVo<DataGrid<RemoteStaffVo>>>(){});
		List<RemoteStaffVo> remoteStaffVoList = ((DataGrid) responseVo.getResult()).getList();
		return remoteStaffVoList;
	}
	
}
