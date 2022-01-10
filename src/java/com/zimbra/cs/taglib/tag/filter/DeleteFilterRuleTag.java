// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.filter;

import com.zimbra.common.service.ServiceException;
import com.zimbra.common.util.StringUtil;
import com.zimbra.cs.taglib.tag.ZimbraSimpleTag;
import com.zimbra.cs.taglib.bean.ZTagLibException;
import com.zimbra.client.ZFilterRule;
import com.zimbra.client.ZFilterRules;
import com.zimbra.client.ZMailbox;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeleteFilterRuleTag extends ZimbraSimpleTag {

    private String mName;

    public void setName(String name) { mName = name; }

    public void doTag() throws JspException, IOException {
        try {
            ZMailbox mbox = getMailbox();
            ZFilterRules rules = mbox.getIncomingFilterRules(true);
            List<ZFilterRule> newRules = new ArrayList<ZFilterRule>();
            boolean found = false;
            for (ZFilterRule rule: rules.getRules()) {
                String ruleName = StringUtil.escapeHtml(rule.getName());
                if (ruleName.equalsIgnoreCase(mName)) {
                    found = true;
                } else {
                    newRules.add(rule);
                }
            }
            if (!found)
                throw ZTagLibException.NO_SUCH_FILTER_EXISTS("filter with name "+mName+" doesn't exist", null);
            mbox.saveIncomingFilterRules(new ZFilterRules(newRules));
        } catch (ServiceException e) {
            throw new JspTagException(e);
        }
    }
}
