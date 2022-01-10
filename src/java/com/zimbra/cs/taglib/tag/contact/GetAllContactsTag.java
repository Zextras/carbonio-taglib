// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.contact;

import com.zimbra.common.service.ServiceException;
import com.zimbra.cs.taglib.bean.ZContactBean;
import com.zimbra.cs.taglib.tag.ZimbraSimpleTag;
import com.zimbra.client.ZMailbox;
import com.zimbra.client.ZContact;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class GetAllContactsTag extends ZimbraSimpleTag {

    private String mVar;
    private String mFolderId;
    private String mContactIds;
    private boolean mSync;

    public void setVar(String var) { this.mVar = var; }
    public void setFolderId(String folderid) { this.mFolderId = folderid; }
    public void setContactIds(String ids) { this.mContactIds = ids; }
    public void setSync(boolean sync) { this.mSync = sync; }

    public void doTag() throws JspException, IOException {
        JspContext jctxt = getJspContext();
        try {
            ZMailbox mbox = getMailbox();
            List<ZContactBean> contactBeanList = new ArrayList<ZContactBean>();
            List<ZContact> contactList = mbox.getContactsForFolder(mFolderId, mContactIds, null, false, null);
            for(ZContact contact: contactList) {
              contactBeanList.add(new ZContactBean(contact));
            }
            jctxt.setAttribute(mVar, contactBeanList,  PageContext.PAGE_SCOPE);
        } catch (ServiceException e) {
            throw new JspTagException(e);
        }
    }
}
