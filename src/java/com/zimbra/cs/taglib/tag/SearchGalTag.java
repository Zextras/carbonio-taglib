// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag;

import com.zimbra.common.service.ServiceException;
import com.zimbra.common.soap.AccountConstants;
import com.zimbra.cs.taglib.bean.ZSearchGalResultBean;
import com.zimbra.client.ZMailbox;
import com.zimbra.client.ZMailbox.GalEntryType;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.util.ArrayList;

public class SearchGalTag extends ZimbraSimpleTag {

    private String mVar;
    private GalEntryType mType = null;
    private String mQuery = null;
    private String mDept = null;
    private String mEmail = null;

    public void setVar(String var) { this.mVar = var; }

    public void setType(String type) throws ServiceException { this.mType = GalEntryType.fromString(type); }

    public void setQuery(String query) {
        this.mQuery = query == null ? "" : query;
    }

    public void setDept(String dept) {
        this.mDept =  dept == null ? "" : dept;
    }

    public void setEmail(String email) {
        this.mEmail =  email == null ? "" : email;
    }

    public void doTag() throws JspException, IOException {
        JspContext jctxt = getJspContext();
        try {
            final ArrayList<Object> conditions = new ArrayList<Object>();
            if(mDept != null && !mDept.isEmpty())
            {
                conditions.add(new String[]{AccountConstants.A_DEPARTMENT,"has", mDept});
            }
            if(mEmail != null && !mEmail.isEmpty())
            {
                final ArrayList<Object> emailCond = new ArrayList<Object>();
                emailCond.add(new String[]{AccountConstants.E_EMAIL,"has", mEmail});
                emailCond.add(new String[]{AccountConstants.E_EMAIL2,"has", mEmail});
                emailCond.add(new String[]{AccountConstants.E_EMAIL3,"has", mEmail});
                conditions.add(emailCond);
            }
            ZMailbox mbox = getMailbox();
            jctxt.setAttribute(mVar, new ZSearchGalResultBean(mbox.searchGal(mQuery, conditions, mType)),  PageContext.PAGE_SCOPE);
        } catch (ServiceException e) {
            throw new JspTagException(e);
        }
    }
}
