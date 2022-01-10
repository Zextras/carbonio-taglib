// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag;

import com.zimbra.cs.taglib.bean.ZMessageBean;
import com.zimbra.cs.taglib.bean.ZMessageComposeBean;
import com.zimbra.cs.taglib.bean.ZMimePartBean;

import javax.servlet.jsp.JspException;
import java.io.IOException;
import java.util.List;

public class FixupMessageComposeTag extends ZimbraSimpleTag {


    private ZMessageComposeBean mCompose;
    private ZMessageBean mMessage;
    private boolean mNewAttachments;


    public void setMessage(ZMessageBean message) { mMessage = message; }

    public void setCompose(ZMessageComposeBean compose) { mCompose = compose; }

    public void setNewattachments(boolean newAttachments) { mNewAttachments = newAttachments; }

    public void doTag() throws JspException, IOException {
        List<ZMimePartBean> attachments = mMessage.getAttachments();
        mCompose.setOrignalAttachments(attachments);
        if (mNewAttachments) {
            for (ZMimePartBean part : attachments) {
                mCompose.setCheckedAttachmentName(part.getPartName(),(part.getContentId() == null || part.getContentId().equals("") ? "true" : part.getContentId()));
            }
        }
    }
}
