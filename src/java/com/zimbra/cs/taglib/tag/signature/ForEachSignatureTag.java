// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.signature;

import com.zimbra.common.service.ServiceException;
import com.zimbra.cs.taglib.tag.ZimbraSimpleTag;
import com.zimbra.client.ZMailbox;
import com.zimbra.client.ZSignature;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.JspFragment;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ForEachSignatureTag extends ZimbraSimpleTag {

    private String mVar;
    private boolean mRefresh;

    public void setVar(String var) { this.mVar = var; }
    public void setRefresh(boolean refresh) { this.mRefresh = refresh; }

    public void doTag() throws JspException, IOException {
        try {
            JspFragment body = getJspBody();
            if (body == null) return;
            JspContext jctxt = getJspContext();
            ZMailbox mbox = getMailbox();
            List<ZSignature> sigs = mbox.getAccountInfo(mRefresh).getSignatures();
            Collections.sort(sigs);
            for (ZSignature sig: sigs) {
                jctxt.setAttribute(mVar, sig);
                body.invoke(null);
            }
        } catch (ServiceException e) {
            throw new JspTagException(e);
        }
    }
}
