// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.contact;

import com.zimbra.common.service.ServiceException;
import com.zimbra.cs.taglib.bean.ZTagLibException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;

public class ModifyContactTag extends ContactOpTag {

    private String mId;
    private String mVar;
    private String mFolderid;
    private String mTagids;
    private boolean mReplace;

    public void setId(String id) { mId = id; }
    public void setFolderid(String folderid) { mFolderid = folderid; }
    public void setVar(String var) { mVar = var; }
    public void setReplace(boolean replace) { mReplace = replace; }
    public void setTags(String tagids) { mTagids = tagids; } 

    public void doTag() throws JspException, IOException {
        try {
            getJspBody().invoke(null);

            if (mAttrs.isEmpty() || allFieldsEmpty()){
                throw ZTagLibException.EMPTY_CONTACT("can't set all fields to blank", null);
            }

            String id = (mId == null || mId.length() == 0) ?
                    getMailbox().createContactWithMembers(mFolderid, mTagids, mAttrs, mMembers).getId() :
                    getMailbox().modifyContactWithMembers(mId, mReplace, mAttrs, mMembers).getId();
            getJspContext().setAttribute(mVar, id, PageContext.PAGE_SCOPE);
        } catch (ServiceException e) {
            throw new JspTagException(e);
        }
    }
}
