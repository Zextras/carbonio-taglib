// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.filter;

import com.zimbra.common.service.ServiceException;
import com.zimbra.cs.taglib.tag.ZimbraSimpleTag;
import com.zimbra.client.ZFilterCondition.BodyOp;
import com.zimbra.client.ZFilterCondition.ZBodyCondition;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

public class BodyConditionTag extends ZimbraSimpleTag {

    private BodyOp mOp;
    private String mValue;

    public void setValue(String value) { mValue = value; }
    public void setOp(String op) throws ServiceException { mOp = BodyOp.fromString(op); }

    public void doTag() throws JspException {
        FilterRuleTag rule = (FilterRuleTag) findAncestorWithClass(this, FilterRuleTag.class);
        if (rule == null)
                throw new JspTagException("The bodyCondition tag must be used within a filterRule tag");
        rule.addCondition(new ZBodyCondition(mOp, mValue));
    }

}
