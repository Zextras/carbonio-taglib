// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.filter;

import com.zimbra.common.filter.Sieve;
import com.zimbra.common.service.ServiceException;
import com.zimbra.cs.taglib.tag.ZimbraSimpleTag;
import com.zimbra.client.ZFilterCondition.HeaderOp;
import com.zimbra.client.ZFilterCondition.ZAddressCondition;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

public class AddressConditionTag extends ZimbraSimpleTag {
    private String mHeaderName;
    private Sieve.AddressPart mPart;
    private HeaderOp mOp;
    private String mValue;

    public void setValue(String value) { mValue = value; }
    public void setName(String name) { mHeaderName = name; }
    public void setOp(String op) throws ServiceException { mOp = HeaderOp.fromString(op); }
    public void setPart(String part) throws ServiceException {mPart = Sieve.AddressPart.fromString(part); }

    public void doTag() throws JspException {
        FilterRuleTag rule = (FilterRuleTag) findAncestorWithClass(this, FilterRuleTag.class);
        if (rule == null)
            throw new JspTagException("The addressCondition tag must be used within a filterRule tag");
        rule.addCondition(new ZAddressCondition(mHeaderName, mPart, mOp, mValue));
    }

}
