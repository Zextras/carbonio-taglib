// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.voice;

import com.zimbra.cs.taglib.tag.ZimbraSimpleTag;
import com.zimbra.client.ZMailbox;
import com.zimbra.client.ZPhoneAccount;
import com.zimbra.common.service.ServiceException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspTagException;
import java.io.IOException;
import java.util.List;

public class CheckVoiceStatusTag extends ZimbraSimpleTag {
	private String mVar;

	public void setVar(String var) { mVar = var; }

	public void doTag() throws JspException, IOException {
		try {
			ZMailbox mbox = getMailbox();
			List<ZPhoneAccount> accounts = mbox.getAllPhoneAccounts();
			Boolean ok = accounts.size() > 0;
			getJspContext().setAttribute(mVar, ok, PageContext.PAGE_SCOPE);
		} catch (ServiceException e) {
			getJspContext().setAttribute(mVar, Boolean.FALSE, PageContext.PAGE_SCOPE);
			throw new JspTagException(e);
		}
	}
}