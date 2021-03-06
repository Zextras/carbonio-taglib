// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag;

import com.zimbra.cs.taglib.bean.ZUserAgentBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;

public class GetUserAgentTag extends ZimbraSimpleTag {

    private static final String UA_SESSION = "ZUserAgentBean.SESSION";
    private String mVar;
    private boolean mSession = true;

    public void setVar(String var) { this.mVar = var; }
    public void setSession(boolean session) { this.mSession = session; }

    public void doTag() throws JspException, IOException {
        JspContext ctxt = getJspContext();
        ctxt.setAttribute(mVar, getUserAgent(ctxt, mSession),  PageContext.REQUEST_SCOPE);
    }

    public static ZUserAgentBean getUserAgent(JspContext ctxt, boolean session) {
        PageContext pctxt = (PageContext) ctxt;
        HttpServletRequest req = (HttpServletRequest) pctxt.getRequest();
        ZUserAgentBean ua;
        if (session) {
            ua = (ZUserAgentBean) ctxt.getAttribute(UA_SESSION, PageContext.SESSION_SCOPE);
            if ( ua == null) {
                ua = new ZUserAgentBean(req.getHeader("User-Agent"));
                ctxt.setAttribute(UA_SESSION, ua,  PageContext.SESSION_SCOPE);
            }

        } else {
            ua = new ZUserAgentBean(req.getHeader("User-Agent"));
        }
        return ua;
    }
    
}
