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

import com.puhui.app.service.SwaggerService;
import com.puhui.nosql.redis.JedisTemplate;
import com.puhui.nosql.redis.JedisTemplate.JedisAction;
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
    private SwaggerService swaggerService;

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
                permissionCode = swaggerService.permissions(staff.getId());
                setStaffPermissionInfoInRedisCache(staff.getId(), permissionCode);
            } else {
//                permissionCode = jedisTemplate.smembers("staff-" + String.valueOf(staff.getId()));
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
            RemoteStaffVo staffVo = swaggerService.username(realName);
            changeBossOrgCode(staffVo, casToken.getRequest());
            Set<String> permissionCode = swaggerService.permissions(staffVo.getId());
            setStaffPermissionInfoInRedisCache(staffVo.getId(), permissionCode);

            log.info("PuhuiCasRealm AuthenticationInfo end ..");
            return new SimpleAuthenticationInfo(staffVo, token.getCredentials(), realName);
        } catch (Exception e) {
            log.error("认证失败！", e);
            throw new AuthenticationException(e);
        }
    }

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
//            jedisTemplate.sadd("staff-" + String.valueOf(staffId), permissionCode);
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
