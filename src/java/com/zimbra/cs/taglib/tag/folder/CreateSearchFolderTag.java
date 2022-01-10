// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.folder;

import com.zimbra.common.service.ServiceException;
import com.zimbra.cs.taglib.tag.ZimbraSimpleTag;
import com.zimbra.cs.taglib.bean.ZFolderBean;
import com.zimbra.client.ZFolder;
import com.zimbra.soap.type.SearchSortBy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspTagException;
import java.io.IOException;

public class CreateSearchFolderTag extends ZimbraSimpleTag {

    private String mParentId;
    private String mName;
    private String mVar;
    private ZFolder.Color mColor;
    private SearchSortBy mSortBy;
    private String mTypes;
    private String mQuery;

    public void setParentid(String parentid) { mParentId = parentid; }
    public void setName(String name) { mName = name; }
    public void setTypes(String types) { mTypes = types; }
    public void setQuery(String query) { mQuery = query; }
    public void setVar(String var) { mVar = var; }
    public void setColor(String color) throws ServiceException { mColor = ZFolder.Color.fromString(color); }
    public void setSort(String sort) throws ServiceException { mSortBy = SearchSortBy.fromString(sort); }

    @Override
    public void doTag() throws JspException, IOException {
        try {
            ZFolderBean result = new ZFolderBean(getMailbox().createSearchFolder(mParentId, mName, mQuery, mTypes, mSortBy, mColor));
            getJspContext().setAttribute(mVar, result, PageContext.PAGE_SCOPE);
        } catch (ServiceException e) {
            throw new JspTagException(e);
        }
    }
}
