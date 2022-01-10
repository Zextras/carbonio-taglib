// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.contact;

import com.zimbra.cs.taglib.tag.ZimbraSimpleTag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

public class MemberTag extends ZimbraSimpleTag {

    private String mType;
    private String mId;

    public void setValue(String value) { mId = value; }
    public void setName(String name) { mType = name; }

    public void doTag() throws JspException {
        ContactOpTag op = (ContactOpTag) findAncestorWithClass(this, ContactOpTag.class);
        if (op == null)
            throw new JspTagException("The field tag must be used within a create/modify contact tag");
        op.addMembers(mId, mType) ;
    }

}
