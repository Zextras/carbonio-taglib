// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag;

import com.zimbra.client.ZMailbox;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import java.io.IOException;

public class ClearSearchCacheTag extends ZimbraSimpleTag {

    private String mType;

    public void setType(String type) { this.mType = type; }

    public void doTag() throws JspException, IOException {
        JspContext jctxt = getJspContext();
        ZMailbox mbox = getMailbox();
        mbox.clearSearchCache(mType);
    }
}
