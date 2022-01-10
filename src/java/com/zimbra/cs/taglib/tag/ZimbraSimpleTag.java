// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.zimbra.cs.taglib.ZJspSession;
import com.zimbra.client.ZMailbox;

public abstract class ZimbraSimpleTag extends SimpleTagSupport {

    public ZMailbox getMailbox() throws JspException {
        return ZJspSession.getZMailbox((PageContext) getJspContext());
    }

}
