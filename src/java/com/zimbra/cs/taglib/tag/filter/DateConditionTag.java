// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.filter;

import com.zimbra.common.service.ServiceException;
import com.zimbra.cs.taglib.tag.ZimbraSimpleTag;
import com.zimbra.cs.taglib.bean.ZTagLibException;
import com.zimbra.client.ZFilterCondition.DateOp;
import com.zimbra.client.ZFilterCondition.ZDateCondition;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConditionTag extends ZimbraSimpleTag {

    private DateOp mOp;
    private String mValue;


    public void setValue(String value) { mValue = value; }
    public void setOp(String op) throws ServiceException { mOp = DateOp.fromString(op); }

    public void doTag() throws JspException {
        try {
            FilterRuleTag rule = (FilterRuleTag) findAncestorWithClass(this, FilterRuleTag.class);
            if (rule == null)
                throw new JspTagException("The dateCondition tag must be used within a filterRule tag");
            if (mValue == null || mValue.equals("")) {
                mValue = new SimpleDateFormat("yyyyMMdd").format(new Date());
            }
            rule.addCondition(new ZDateCondition(mOp, mValue));
        } catch (ServiceException e) {
            throw new JspTagException(ZTagLibException.INVALID_FILTER_DATE(e.getMessage(), e));
        }
    }

}
