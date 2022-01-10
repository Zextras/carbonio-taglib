// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.contact;

import com.zimbra.common.mailbox.ContactConstants;
import com.zimbra.common.service.ServiceException;
import com.zimbra.cs.mailbox.Contact;
import com.zimbra.cs.taglib.tag.ZimbraSimpleTag;

import javax.servlet.jsp.JspTagException;
import java.util.HashMap;
import java.util.Map;

public class ContactOpTag extends ZimbraSimpleTag {

    protected boolean mForce;
    protected Map<String, String> mAttrs = new HashMap<String,String>();
    protected Map<String, String> mMembers = new HashMap<String,String>();

    public void setForce(boolean force) { mForce = force; }

    public void addAttr(String name, String value) throws JspTagException {
        if (!mForce) {
            try {
                ContactConstants.Attr.fromString(name); // make sure it is a known attr name
            } catch (ServiceException e) {
                throw new JspTagException(e);
            }
        }
        mAttrs.put(name, value);
    }
    
    public void addMembers(String id, String type) throws JspTagException {
        mMembers.put(id, type);
    }

    protected boolean allFieldsEmpty() {
        for (Map.Entry<String,String> entry : mAttrs.entrySet()) {
            if (entry.getValue() != null && entry.getValue().trim().length() > 0 && !entry.getKey().equalsIgnoreCase(ContactConstants.A_fileAs)){
                return false;
            }
        }
        return true;
    }
}
