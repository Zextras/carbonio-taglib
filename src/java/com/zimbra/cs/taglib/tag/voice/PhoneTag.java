// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.voice;

import com.zimbra.cs.taglib.tag.ZimbraSimpleTag;

import com.zimbra.common.service.ServiceException;
//import com.zimbra.cs.account.Provisioning;

import com.zimbra.cs.taglib.bean.ZPhoneBean;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;

public class PhoneTag extends ZimbraSimpleTag {

    protected String mVar;
    protected String mName;
    protected String mDisplayVar;
    protected String mErrorVar;
    
    public void setVar(String var) {
	    this.mVar = var;
    }
        
    public void setName(String name) {
	    this.mName = name;
    }
    
    public String getName() {
	    return this.mName;
    }
    
    public void setDisplayVar(String displayVar) {
	    this.mDisplayVar = displayVar;
    }

    public void setErrorVar(String errorVar) {
	    this.mErrorVar = errorVar;
    }
    
    public void doTag() throws JspTagException {
	try {
	    ZPhoneBean phone = new ZPhoneBean(this.mName);
	    
	    String validity = phone.getValidity();
	    if (mErrorVar!=null) getJspContext().setAttribute(mErrorVar, validity, PageContext.PAGE_SCOPE);
	    if (mDisplayVar!=null) getJspContext().setAttribute(mDisplayVar, phone.getDisplay(), PageContext.PAGE_SCOPE);
	    if (mVar!=null) getJspContext().setAttribute(mVar, validity.equals(ZPhoneBean.VALID), PageContext.PAGE_SCOPE);
	} catch (ServiceException e) {
	    throw new JspTagException(e);
	}
    }
}
