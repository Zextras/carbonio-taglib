// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.msg;

import com.zimbra.common.service.ServiceException;
import com.zimbra.cs.taglib.tag.ZimbraSimpleTag;
import com.zimbra.cs.taglib.bean.ZActionResultBean;
import com.zimbra.client.ZMailbox.ZActionResult;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspTagException;

public class MarkMessageSpamTag extends ZimbraSimpleTag {

    private String mId;
    private boolean mSpam;
    private String mFolderid;
    private String mVar;

    public void setVar(String var) { mVar = var; }
    public void setId(String id) { mId = id; }
    public void setSpam(boolean spam) { mSpam = spam; } 
    public void setFolderidid(String folderid) { mFolderid = folderid; }

    public void doTag() throws JspException {
        try {
            ZActionResult result = getMailbox().markMessageSpam(mId, mSpam, mFolderid);
            getJspContext().setAttribute(mVar, new ZActionResultBean(result), PageContext.PAGE_SCOPE);
        } catch (ServiceException e) {
            throw new JspTagException(e);
        }
    }
}