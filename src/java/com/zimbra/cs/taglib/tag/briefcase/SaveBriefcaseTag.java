// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.briefcase;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;

import org.apache.commons.fileupload.FileItem;

import com.zimbra.client.ZMailbox;
import com.zimbra.common.service.ServiceException;
import com.zimbra.cs.taglib.bean.ZMessageBean;
import com.zimbra.cs.taglib.bean.ZMessageComposeBean;
import com.zimbra.cs.taglib.tag.ZimbraSimpleTag;

public class SaveBriefcaseTag extends ZimbraSimpleTag {

    private String mVar;

    private ZMessageComposeBean mCompose;
    private ZMessageBean mMessage;
    private String mFolderId;

    public void setCompose(ZMessageComposeBean compose) { mCompose = compose; }
    public void setMessage(ZMessageBean message) { mMessage = message; }

    public void setFolderId(String folderId) { mFolderId = folderId; }
    public void setVar(String var) { this.mVar = var; }

    @Override
    public void doTag() throws JspException, IOException {
        JspContext jctxt = getJspContext();
        PageContext pc = (PageContext) jctxt;

        try {

            ZMailbox mbox = getMailbox();

            if (mCompose != null && mCompose.getHasFileItems()) {
                List<FileItem> mFileItems = mCompose.getFileItems();
                int num = 0;
                for (FileItem item : mFileItems) {
                    if (item.getSize() > 0) num++;
                }
                String[] briefIds = new String[num];
                int i=0;
                try {
                    for (FileItem item : mFileItems) {
                        if (item.getSize() > 0) {
                            Map<String, byte[]> attachment = new HashMap<String, byte[]>();
                            attachment.put(item.getName(), item.get());
                            String attachmentUploadId = mbox.uploadAttachments(attachment,
                                1000 * 60);
                            briefIds[i++] = mbox.createDocument(mFolderId, item.getName(),
                                attachmentUploadId);
                        }
                    }
                } finally {
                    for (FileItem item : mFileItems) {
                        try {
                            item.delete();
                        } catch (Exception e) {
                            /* TODO: need logging infra */ }
                    }
                }

                jctxt.setAttribute(mVar, briefIds, PageContext.PAGE_SCOPE);
            }
        } catch (ServiceException e) {
            throw new JspTagException(e.getMessage(), e);
        }

    }

}
