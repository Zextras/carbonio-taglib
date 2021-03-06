// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag;

import com.zimbra.common.service.ServiceException;
import com.zimbra.cs.taglib.bean.ZFileUploaderBean;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;

public class FileUploaderTag extends ZimbraSimpleTag {

    private String mVar;

    public void setVar(String var) { this.mVar = var; }

    public void doTag() throws JspException, IOException {
        JspContext jctxt = getJspContext();
        PageContext pc = (PageContext) jctxt;
        ZFileUploaderBean fup = (ZFileUploaderBean) jctxt.getAttribute(mVar, PageContext.REQUEST_SCOPE);
        if (fup == null) {
            try {
                jctxt.setAttribute(mVar, new ZFileUploaderBean(pc, getMailbox()), PageContext.REQUEST_SCOPE);
            } catch (ServiceException e) {
                throw new JspTagException("file upload failed", e);
            }
        }
    }
}
