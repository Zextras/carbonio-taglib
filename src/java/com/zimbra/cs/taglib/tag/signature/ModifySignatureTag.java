// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.signature;

import com.zimbra.common.service.ServiceException;
import com.zimbra.cs.taglib.tag.ZimbraSimpleTag;
import com.zimbra.client.ZSignature;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import java.io.IOException;

public class ModifySignatureTag extends ZimbraSimpleTag {

    private String mId;
    private String mName;
    private String mValue;
    private String mType = "text/plain";
    
    public void setId(String id) { mId = id; }
    public void setName(String name) { mName = name; }
    public void setValue(String value) { mValue = value; }
    public void setType(String type) { mType = type; }
    
    public void doTag() throws JspException, IOException {
        try {
            getMailbox().modifySignature(new ZSignature(mId, mName, mValue, mType));
        } catch (ServiceException e) {
            throw new JspTagException(e);
        }
    }
}
