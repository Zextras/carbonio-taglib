// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.conv;

import com.zimbra.common.service.ServiceException;
import com.zimbra.cs.taglib.bean.ZConversationBean;
import com.zimbra.cs.taglib.tag.ZimbraSimpleTag;
import com.zimbra.client.ZConversation;
import com.zimbra.client.ZMailbox;
import com.zimbra.client.ZMailbox.Fetch;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspTagException;
import java.io.IOException;

public class GetConversationTag extends ZimbraSimpleTag {

    private String mVar;
    private String mId;
    private Fetch mFetch;

    public void setVar(String var) { this.mVar = var; }
    
    public void setFetch(String fetch) throws ServiceException { this.mFetch = Fetch.fromString(fetch); }    

    public void setId(String id) { this.mId = id; }

    public void doTag() throws JspException, IOException {
        JspContext jctxt = getJspContext();
        try {
            ZMailbox mbox = getMailbox();
            ZConversation conv = mbox.getConversation(mId, mFetch);
            jctxt.setAttribute(mVar, new ZConversationBean(conv),  PageContext.PAGE_SCOPE);
        } catch (ServiceException e) {
            throw new JspTagException(e);
        }
    }
}
