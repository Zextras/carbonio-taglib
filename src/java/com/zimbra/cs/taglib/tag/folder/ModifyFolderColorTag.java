// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.folder;

import com.zimbra.common.service.ServiceException;
import com.zimbra.cs.taglib.tag.ZimbraSimpleTag;
import com.zimbra.client.ZFolder;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import java.io.IOException;

public class ModifyFolderColorTag extends ZimbraSimpleTag {

    private String mId;
    private ZFolder.Color mColor;

    public void setId(String id) { mId = id; }
    public void setColor(String color) throws ServiceException { mColor = ZFolder.Color.fromString(color); }


    public void doTag() throws JspException, IOException {
        try {
            getMailbox().modifyFolderColor(mId, mColor);
        } catch (ServiceException e) {
            throw new JspTagException(e);
        }
    }
}
