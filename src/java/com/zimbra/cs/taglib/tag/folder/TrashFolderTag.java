// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.folder;

import com.zimbra.cs.taglib.tag.ZimbraSimpleTag;
import com.zimbra.common.service.ServiceException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import java.io.IOException;

public class TrashFolderTag extends ZimbraSimpleTag {

    private String mId;

    public void setId(String id) { mId = id; }

    public void doTag() throws JspException, IOException {
        try {
            getMailbox().trashFolder(mId);
        } catch (ServiceException e) {
            throw new JspTagException(e);
        }
    }
}
