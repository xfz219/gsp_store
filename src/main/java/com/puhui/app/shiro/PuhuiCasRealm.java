/**
 * Copyright(c) 2013-2013 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.puhui.app.shiro;

import java.util.Set;

import javax.servlet.ServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.puhui.nosql.redis.JedisTemplate;
import com.puhui.nosql.redis.JedisTemplate.JedisAction;
import com.puhui.uc.api.service.RemoteUserCentreService;
import com.puhui.uc.vo.RemoteRbacRoleVo;
import com.puhui.uc.vo.RemoteStaffVo;

import redis.clients.jedis.Jedis;

/**
 * @author ZhangMing
 * @see org.apache.shiro.cas.CasRealm
 */
public class PuhuiCasRealm extends CasRealm {

    private static final Logger log = LoggerFactory.getLogger(PuhuiCasRealm.class);

    /** 密码盐 */
    public static final String SALT = "wAteRmE10n";
    /** 散列的 弹性攻击次数 与配置文件 必须保持一致 */
    public static final int HASHITERATIONS = 5;
    /** 加密方式 */
    public static final String ALGORITHM_NAME = "md5";

    @Autowired
    private RemoteUserCentreService remoteUserCenteCentreService;

    private JedisTemplate jedisTemplate;

    public PuhuiCasRealm() {
        super();
        // 设置认证token的实现类
        setAuthenticationTokenClass(CasToken.class);
    }

    /**
     * 重写shiro的认证方法,将角色的name与权限的code,保存在授权结果中,用于后续程序的校验
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        RemoteStaffVo staff = (RemoteStaffVo) principals.getPrimaryPrincipal();
        if (null == staff) {
            return null;
        } else {
            SimpleAuthorizationInfo result = new SimpleAuthorizationInfo();
            Set<RemoteRbacRoleVo> roles = staff.getRoles();
            for (RemoteRbacRoleVo rbacRole : roles) {
                result.addRole(rbacRole.getName());
            }
            Set<String> permissionCode = null;
            if (isPermissionInfoNotInRedisCacheByStaffId(staff.getId())) {
                permissionCode = remoteUserCenteCentreService.queryAllPermissionByStaffId(staff.getId());
                setStaffPermissionInfoInRedisCache(staff.getId(), permissionCode);
            } else {
                permissionCode = jedisTemplate.smembers("staff-" + String.valueOf(staff.getId()));
            }
            result.addStringPermissions(permissionCode);
            return result;
        }
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("PuhuiCasRealm AuthenticationInfo start ..");
        try {
            super.doGetAuthenticationInfo(token);
            PuhuiCasToken casToken = (PuhuiCasToken) token;
            String realName = (String) casToken.getPrincipal();
            log.info("the realNam is {}", realName);

            RemoteStaffVo staffVo = remoteUserCenteCentreService.queryStaffByUsername(realName);
            changeBossOrgCode(staffVo, casToken.getRequest());

            Set<String> permissionCode = remoteUserCenteCentreService.queryAllPermissionByStaffId(staffVo.getId());
            setStaffPermissionInfoInRedisCache(staffVo.getId(), permissionCode);

            log.info("PuhuiCasRealm AuthenticationInfo end ..");
            return new SimpleAuthenticationInfo(staffVo, token.getCredentials(), realName);
        } catch (Exception e) {
            log.error("认证失败！");
            throw new AuthenticationException(e);
        }
    }

    /**
     * 将Ｖｏ对象转成实体对象
     * 
     * @author ZhangMing
     * @param staffVo
     * @param staff
     */
    // private void remoteStaffVoToStaffEntity(RemoteStaffVo staffVo, Staff
    // staff) {
    // staff.setId(staffVo.getId());
    // staff.setUsername(staffVo.getUsername());
    // staff.setPassword(staffVo.getPassword());
    // staff.setRealName(staffVo.getRealName());
    // staff.setEmployeeNo(staffVo.getEmployeeNo());
    // staff.setEmail(staffVo.getEmail());
    // staff.setEnabled(staffVo.isEnabled());
    // staff.setMobile(staffVo.getMobile());
    // staff.setPhone(staffVo.getPhone());
    // staff.setEntryTime(staffVo.getEntryTime());
    // staff.setDisabledTime(staffVo.getDisabledTime());
    // staff.setCreateTime(staffVo.getCreateTime());
    // staff.setUpdateTime(staffVo.getUpdateTime());
    // staff.setLastLoginTime(staffVo.getLastLoginTime());
    // staff.setResignationTime(staffVo.getResignationTime());
    // staff.setIssueCertStatus(StaffCertStatus.valueOf(staffVo.getIssueCertStatus()));
    // staff.setSendMail(staffVo.getSendMail());
    // staff.setCertEnable(staffVo.getCertEnable());
    // staff.setSslCertImportPassword(staffVo.getSslCertImportPassword());
    // staff.setPassword(staffVo.getPassword());
    // staff.setModifyPasswordTime(staffVo.getModifyPasswordTime());
    // staff.setPositionId(staffVo.getPositionId());
    // staff.setPositionType(PositionType.valueOf(staffVo.getPositionType()));
    //
    // RemoteOrganizationVo organizationVo = staffVo.getOrganizationVo();
    // Organization organization = new Organization();
    // organization.setId(organizationVo.getId());
    // organization.setName(organizationVo.getName());
    // organization.setLevel(organizationVo.getLevel());
    // organization.setBranchName(organization.getBranchName());
    // organization.setCreateTime(organizationVo.getCreateTime());
    // organization.setUpdateTime(organizationVo.getUpdateTime());
    // organization.setDeleted(organizationVo.getDeleted());
    // organization.setOrganizationType(OrganizationType.valueOf(organizationVo.getOrganizationType()));
    // organization.setCode(organizationVo.getCode());
    // organization.setAreaShopCode(organization.getAreaShopCode());
    // organization.setAreaName(organizationVo.getAreaName());
    // organization.setOnline(organizationVo.isOnline());
    //
    // if (null != staffVo.getOrganizationVo().getParentVo()) {
    // Organization parentOrganization = new Organization();
    // parentOrganization.setId(organizationVo.getParentVo().getId());
    // parentOrganization.setName(organizationVo.getParentVo().getName());
    // parentOrganization.setLevel(organizationVo.getParentVo().getLevel());
    // parentOrganization.setBranchName(organization.getBranchName());
    // parentOrganization.setCreateTime(organizationVo.getParentVo().getCreateTime());
    // parentOrganization.setUpdateTime(organizationVo.getParentVo().getUpdateTime());
    // parentOrganization.setDeleted(organizationVo.getParentVo().getDeleted());
    // parentOrganization.setOrganizationType(OrganizationType.valueOf(organizationVo.getParentVo()
    // .getOrganizationType()));
    // parentOrganization.setCode(organizationVo.getParentVo().getCode());
    // parentOrganization.setAreaShopCode(organization.getAreaShopCode());
    // parentOrganization.setAreaName(organizationVo.getParentVo().getAreaName());
    // parentOrganization.setOnline(organizationVo.getParentVo().isOnline());
    // organization.setParent(parentOrganization);
    // }
    //
    // staff.setOrganization(organization);
    //
    // Set<RemoteRbacRoleVo> remoteRbacRoleVos = staffVo.getRoles();
    // Set<RbacRole> roles = Sets.newHashSet();
    // if (null != remoteRbacRoleVos && remoteRbacRoleVos.size() > 0) {
    // Iterator<RemoteRbacRoleVo> iterator = remoteRbacRoleVos.iterator();
    // while (iterator.hasNext()) {
    // roles.add(RemoteRbacRoleVoToRbacRole(iterator.next()));
    //
    // }
    // }
    // staff.setRoles(roles);
    //
    // }

    /**
     * 将Ｖｏ对象转成实体对象
     * 
     * @author ZhangMing
     * @param role
     * @return
     */

    // private RbacRole RemoteRbacRoleVoToRbacRole(RemoteRbacRoleVo roleVo) {
    // RbacRole role = new RbacRole();
    // role.setId(roleVo.getId());
    // role.setName(roleVo.getName());
    // role.setDefaulted(roleVo.getDefaulted());
    // role.setDeleted(roleVo.getDefaulted());
    // role.setDescription(role.getDescription());
    // role.setUpdateTime(roleVo.getUpdateTime());
    // role.setCreateTime(roleVo.getCreateTime());
    // return role;
    // }

    /**
     * 更改boss的orgCode，让其在任何一个系统都是老大
     * 
     * @param staff
     * @param request
     */
    private void changeBossOrgCode(RemoteStaffVo staff, ServletRequest request) {
        if (staff.getOrganizationVo().getCode().equals("0")) {
            String project = request.getServletContext().getContextPath();
            if (project != null & project.contains("lend")) {
                staff.getOrganizationVo().setCode("RPA");
            } else if (project != null & project.contains("risk")) {
                staff.getOrganizationVo().setCode("B");
            } else if (project != null & project.contains("finance")) {
                staff.getOrganizationVo().setCode("D");
            } else if (project != null & project.contains("wealth")) {
                staff.getOrganizationVo().setCode("C");
            }
        }
    }

    public JedisTemplate getJedisTemplate() {
        return jedisTemplate;
    }

    public void setJedisTemplate(JedisTemplate jedisTemplate) {
        this.jedisTemplate = jedisTemplate;
    }

    /**
     * 将权限信息放入redis
     * 
     * @author ZhangMing
     * @param staffId
     * @param permissionCode
     */
    private void setStaffPermissionInfoInRedisCache(long staffId, Set<String> permissionCodes) {
        for (String permissionCode : permissionCodes) {
            jedisTemplate.sadd("staff-" + String.valueOf(staffId), permissionCode);
        }
    }

    /**
     * 判断 key 是否在 redis 中
     * 
     * @author ZhangMing
     * @param staffId
     * @return
     */
    private boolean isPermissionInfoNotInRedisCacheByStaffId(final long staffId) {
        return jedisTemplate.execute(new JedisAction<Boolean>() {
            @Override
            public Boolean action(Jedis jedis) {
                return jedis.scard("staff-" + String.valueOf(staffId)) == 0;
            }
        });
    }
}
