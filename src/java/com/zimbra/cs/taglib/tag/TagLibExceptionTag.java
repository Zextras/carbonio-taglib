// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag;

import com.zimbra.cs.taglib.bean.ZTagLibException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

public class TagLibExceptionTag extends ZimbraSimpleTag {

    private String mCode;
    private String mMessage;

    public void setCode(String code) { mCode = code; }
    public void setMessage(String message) { mMessage = message; }

    public void doTag() throws JspException {
        throw new JspTagException(new ZTagLibException(mCode, mMessage));
    }

}
