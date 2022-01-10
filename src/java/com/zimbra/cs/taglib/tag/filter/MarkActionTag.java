// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.filter;

import com.zimbra.common.service.ServiceException;
import com.zimbra.cs.taglib.tag.ZimbraSimpleTag;
import com.zimbra.client.ZFilterAction.MarkOp;
import com.zimbra.client.ZFilterAction.ZMarkAction;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

public final class MarkActionTag extends ZimbraSimpleTag {

    private MarkOp op;

    public void setOp(String op) throws ServiceException {
        this.op = MarkOp.fromString(op);
    }

    @Override
    public void doTag() throws JspException {
        FilterRuleTag rule = (FilterRuleTag) findAncestorWithClass(this, FilterRuleTag.class);
        if (rule == null) {
            throw new JspTagException("The markAction tag must be used within a filterRule tag");
        }
        rule.addAction(new ZMarkAction(op));
    }

}
