package com.puhui.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.puhui.app.service.LendUcService;
import com.puhui.app.service.SwaggerService;
import com.puhui.app.vo.OrganizationVo;
import com.puhui.uc.vo.RemoteOrganizationVo;


@Service
public class LendUcServiceImpl implements LendUcService{

	@Autowired
	private SwaggerService swaggerService;
	
	@Override
	public Object getOrgTree(String organizationCode) {
		  List<OrganizationVo> or=new ArrayList<OrganizationVo>();
	        List<RemoteOrganizationVo> organizationList = swaggerService.like(organizationCode);
	        if (!CollectionUtils.isEmpty(organizationList)) {
	            for (RemoteOrganizationVo remoteOrganizationVo : organizationList) {
	            	OrganizationVo oe = new OrganizationVo();
	                BeanUtils.copyProperties(remoteOrganizationVo, oe);
	                if (remoteOrganizationVo.getParentVo() != null) {
	                    oe.setPid(remoteOrganizationVo.getParentVo().getId());
	                    oe.setpName(remoteOrganizationVo.getParentVo().getName());
	                }
	                or.add(oe);
	            }
	        }
	        return or;
	}
	
	@Override
	public Object getOrgTreeShop(String organizationCode) {
		  List<OrganizationVo> or=new ArrayList<>();
		  List<RemoteOrganizationVo> organizationList = swaggerService.like(organizationCode);
	        if (!CollectionUtils.isEmpty(organizationList)) {
	            for (RemoteOrganizationVo remoteOrganizationVo : organizationList) {
	            	OrganizationVo oe = new OrganizationVo();
	                BeanUtils.copyProperties(remoteOrganizationVo, oe);
	                if (remoteOrganizationVo.getParentVo() != null) {
	                    oe.setPid(remoteOrganizationVo.getParentVo().getId());
	                    oe.setpName(remoteOrganizationVo.getParentVo().getName());
	                }
	                or.add(oe);
	            }
	        }
	        return or;
	}
	
	
}
