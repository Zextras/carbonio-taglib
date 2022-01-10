// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.filter;

import com.zimbra.common.service.ServiceException;
import com.zimbra.cs.taglib.tag.ZimbraSimpleTag;
import com.zimbra.client.ZFilterCondition.AddressBookOp;
import com.zimbra.client.ZFilterCondition.ZAddressBookCondition;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

public class AddressBookConditionTag extends ZimbraSimpleTag {

    private AddressBookOp op;
    private String header;

    public void setOp(String op) throws ServiceException {
        this.op = AddressBookOp.fromString(op);
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public void doTag() throws JspException {
        FilterRuleTag rule = (FilterRuleTag) findAncestorWithClass(this, FilterRuleTag.class);
        if (rule == null) {
            throw new JspTagException("The addressBookCondition tag must be used within a filterRule tag");
        }
        rule.addCondition(new ZAddressBookCondition(op, header));
    }

}
