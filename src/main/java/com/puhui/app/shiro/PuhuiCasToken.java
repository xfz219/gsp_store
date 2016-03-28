package com.puhui.app.shiro;

import javax.servlet.ServletRequest;

import org.apache.shiro.cas.CasToken;

public class PuhuiCasToken extends CasToken {

    private static final long serialVersionUID = -2571204863453900658L;

    private ServletRequest request;

    public PuhuiCasToken(String ticket) {
        super(ticket);
    }

    public PuhuiCasToken(String ticket, ServletRequest request) {
        super(ticket);
        this.request = request;
    }

    public ServletRequest getRequest() {
        return request;
    }

    public void setRequest(ServletRequest request) {
        this.request = request;
    }

}
