// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag;

import com.zimbra.client.ZDomain;
import com.zimbra.client.ZSoapProvisioning;
import com.zimbra.common.account.Key;
import com.zimbra.common.localconfig.LC;
import com.zimbra.common.soap.AdminConstants;
import com.zimbra.common.util.ZimbraLog;
import com.zimbra.common.util.HttpUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;

public class GetFavIconTag extends ZimbraSimpleTag {

    private String var;
    private HttpServletRequest request;

    public void setVar(String var) {
         this.var = var;
   }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void doTag() throws JspException, IOException {
        try {
            String soapUri = LC.zimbra_admin_service_scheme.value() + LC.zimbra_zmprov_default_soap_server.value() +':' +
                               LC.zimbra_admin_service_port.intValue() + AdminConstants.ADMIN_SERVICE_URI;

            ZSoapProvisioning provisioning = new ZSoapProvisioning();
            provisioning.soapSetURI(soapUri);

            String serverName = this.request.getParameter("customerDomain");

            if (serverName == null) {
                serverName = HttpUtil.getVirtualHost(this.request);
            }

			// get info
            ZDomain info = provisioning.getDomainInfo(Key.DomainBy.virtualHostname, serverName);
            if (info != null) {
                String favicon = info.getSkinFavicon();
                getJspContext().setAttribute(this.var, favicon, PageContext.REQUEST_SCOPE);
            } else {
                ZimbraLog.webclient.debug("unable to get domain info");
            }
        }
        catch (Exception e) {
                ZimbraLog.webclient.debug("error getting favicon:", e);
        }
   }

} // class GetFavIconTag
