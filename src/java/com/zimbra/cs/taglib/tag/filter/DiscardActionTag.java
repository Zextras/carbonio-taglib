// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.filter;

import com.zimbra.cs.taglib.tag.ZimbraSimpleTag;
import com.zimbra.client.ZFilterAction.ZDiscardAction;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

public class DiscardActionTag extends ZimbraSimpleTag {

    public void doTag() throws JspException {
        FilterRuleTag rule = (FilterRuleTag) findAncestorWithClass(this, FilterRuleTag.class);
        if (rule == null)
                throw new JspTagException("The discardAction tag must be used within a filterRule tag");
        rule.addAction(new ZDiscardAction());
    }

}
