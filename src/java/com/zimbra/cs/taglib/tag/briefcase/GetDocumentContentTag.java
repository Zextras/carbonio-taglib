// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.briefcase;

import com.zimbra.common.mime.MimeCompoundHeader;
import com.zimbra.common.service.ServiceException;
import com.zimbra.cs.taglib.bean.ZFolderBean;
import com.zimbra.cs.taglib.bean.ZMailboxBean;
import com.zimbra.cs.taglib.tag.ZimbraSimpleTag;
import com.zimbra.client.ZDocument;
import com.zimbra.client.ZMailbox;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import java.net.URLEncoder;
import java.io.*;
import java.nio.CharBuffer;

public class GetDocumentContentTag extends ZimbraSimpleTag {

    private String mVar;
    private String mId;
    private String mCharset = "UTF-8";
    private ZMailboxBean mMailbox;

    public void setId(String id) { this.mId = id; }
    public void setVar(String var) { this.mVar = var; }
    public void setCharset(String charset) { this.mCharset = charset; }
    public void setBox(ZMailboxBean mailbox) { this.mMailbox = mailbox; }

    public void doTag() throws JspException, IOException {
        JspContext jctxt = getJspContext();
        PageContext pc = (PageContext) jctxt;
        try {
            ZMailbox mbox = mMailbox != null ? mMailbox.getMailbox() :  getMailbox();
			ZDocument doc = mbox.getDocument(this.mId);
            ZFolderBean fb =  new ZFolderBean(mbox.getFolderById(doc.getFolderId()));
            if(fb != null){
                InputStream is = mbox.getRESTResource(fb.getRootRelativePathURLEncoded()+"/"+(URLEncoder.encode(doc.getName(),"UTF-8").replace("+", "%20"))+"?fmt=native");
                InputStreamReader isr = new InputStreamReader(is,mCharset);
                StringBuffer buffer = new StringBuffer();
                Reader in = new BufferedReader(isr);
                int ch;
                while ((ch = in.read()) > -1) {
                    buffer.append((char)ch);
                }
                isr.close();
                in.close();
                jctxt.setAttribute(mVar, buffer.toString().trim(), PageContext.PAGE_SCOPE);
            }

        } catch (ServiceException e) {
            throw new JspTagException(e.getMessage(), e);
        }

    }

}