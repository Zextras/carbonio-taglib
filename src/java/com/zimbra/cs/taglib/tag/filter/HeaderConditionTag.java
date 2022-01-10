// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.filter;

import com.zimbra.common.service.ServiceException;
import com.zimbra.cs.taglib.tag.ZimbraSimpleTag;
import com.zimbra.client.ZFilterCondition.HeaderOp;
import com.zimbra.client.ZFilterCondition.ZHeaderCondition;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

public class HeaderConditionTag extends ZimbraSimpleTag {

    private HeaderOp mOp;
    private String mValue;
    private String mName;

    public void setValue(String value) { mValue = value; }
    public void setName(String name) { mName = name; }
    public void setOp(String op) throws ServiceException { mOp = HeaderOp.fromString(op); }

    public void doTag() throws JspException {
        FilterRuleTag rule = (FilterRuleTag) findAncestorWithClass(this, FilterRuleTag.class);
        if (rule == null)
                throw new JspTagException("The headerCondition tag must be used within a filterRule tag");
        rule.addCondition(new ZHeaderCondition(mName, mOp, mValue));
    }

}
