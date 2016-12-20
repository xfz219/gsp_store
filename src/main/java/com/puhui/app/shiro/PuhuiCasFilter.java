package com.puhui.app.shiro;

import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puhui.uc.vo.RemoteStaffVo;

public class PuhuiCasFilter extends CasFilter {

    private static Logger logger = LoggerFactory.getLogger(PuhuiCasFilter.class);

    private static final String TICKET_PARAMETER = "ticket";

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
            ServletResponse response) throws Exception {
        RemoteStaffVo staff = (RemoteStaffVo) subject.getPrincipal();
        // 添加登入成功的系统日志
        Date now = new Date();
        // 输出日志
        logger.info("{} - 登入 - {}", staff.getUsername(), now);
        return super.onLoginSuccess(token, subject, request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException ae, ServletRequest request,
            ServletResponse response) {
        CasToken casToken = (CasToken) token;
        // 添加尝试登入的系统日志
        Date now = new Date();
        // 输出日志
        logger.info("{} - 尝试登入 - {}", (String) casToken.getPrincipal(), now);
        return super.onLoginFailure(token, ae, request, response);
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String ticket = httpRequest.getParameter(TICKET_PARAMETER);
        return new PuhuiCasToken(ticket, request);
    }
}
