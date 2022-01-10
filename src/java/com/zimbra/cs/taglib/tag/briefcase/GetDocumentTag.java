// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.briefcase;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;

import com.zimbra.client.ZDocument;
import com.zimbra.client.ZMailbox;
import com.zimbra.common.service.ServiceException;
import com.zimbra.cs.taglib.bean.ZDocumentBean;
import com.zimbra.cs.taglib.bean.ZMailboxBean;
import com.zimbra.cs.taglib.tag.ZimbraSimpleTag;

public class GetDocumentTag extends ZimbraSimpleTag {

    private String mVar;
    private String mId;
    private ZMailboxBean mMailbox;

    public void setId(String id) { this.mId = id; }
    public void setVar(String var) { this.mVar = var; }
    public void setBox(ZMailboxBean mailbox) { this.mMailbox = mailbox; }

    @Override
    public void doTag() throws JspException, IOException {
        JspContext jctxt = getJspContext();
        PageContext pc = (PageContext) jctxt;
        try {
            ZMailbox mbox = mMailbox != null ? mMailbox.getMailbox() :  getMailbox();
			ZDocument doc = mbox.getDocument(this.mId);
            jctxt.setAttribute(mVar, new ZDocumentBean(doc), PageContext.PAGE_SCOPE);
        } catch (ServiceException e) {
            throw new JspTagException(e.getMessage(), e);
        }

    }

}
