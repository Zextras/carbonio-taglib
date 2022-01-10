// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.filter;

import com.zimbra.cs.taglib.tag.ZimbraSimpleTag;
import com.zimbra.client.ZFilterAction.ZTagAction;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

public class TagActionTag extends ZimbraSimpleTag {

    private String mTag;

    public void setTag(String tag) { mTag = tag; }

    public void doTag() throws JspException {
        FilterRuleTag rule = (FilterRuleTag) findAncestorWithClass(this, FilterRuleTag.class);
        if (rule == null)
                throw new JspTagException("The tagAction tag must be used within a filterRule tag");
        rule.addAction(new ZTagAction(mTag));
    }

}
