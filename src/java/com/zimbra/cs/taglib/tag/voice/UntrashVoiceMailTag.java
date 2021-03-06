// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.voice;

import com.zimbra.cs.taglib.tag.ZimbraSimpleTag;
import com.zimbra.cs.taglib.bean.ZActionResultBean;
import com.zimbra.client.ZMailbox;
import com.zimbra.common.service.ServiceException;
import com.zimbra.common.soap.VoiceConstants;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;

public class UntrashVoiceMailTag extends ZimbraSimpleTag {

    private String mId;
    private String mPhone;
    private String mVar;

    public void setId(String id) { this.mId = id; }
    public void setPhone(String phone) { this.mPhone = phone; }
    public void setVar(String var) { mVar = var; }

    public void doTag() throws JspException, IOException {
        try {
            ZMailbox mbox = getMailbox();
            ZMailbox.ZActionResult result = mbox.moveVoiceMail(mPhone, mId, VoiceConstants.FID_VOICEMAILINBOX);
            getJspContext().setAttribute(mVar, new ZActionResultBean(result), PageContext.PAGE_SCOPE);
        } catch (ServiceException e) {
            throw new JspTagException(e);
        }
    }
}