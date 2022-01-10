// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;

import com.zimbra.client.ZMailbox;
import com.zimbra.client.ZFolder;
import com.zimbra.client.ZSearchParams;
import com.zimbra.cs.taglib.bean.ZFolderBean;
import com.zimbra.soap.type.SearchSortBy;

public class RetrieveTasksTag extends ZimbraSimpleTag {
    private static final int DEFAULT_TASKS_LIMIT = 50;

    private String mVar;
    private ZFolderBean mTasklist;

    private ZSearchParams params;

    public void setVar(String var) { this.mVar = var; }
    public void setTasklist(ZFolderBean tasklist) { this.mTasklist = tasklist; }


    @Override
    public void doTag() throws JspException, IOException {
            ZMailbox mailbox = getMailbox();

            SearchSortBy mSortBy = SearchSortBy.dateDesc;
            String                 mTypes = ZSearchParams.TYPE_TASK;

            PageContext pageContext = (PageContext) getJspContext();
            SearchContext  sContext = SearchContext.newSearchContext(pageContext);

/*
            sContext.setSfi(mTasklist.getId());
            sContext.setSt(mTypes);
            sContext.setTypes(mTypes);
*/

            ZFolder tasklist = mTasklist.folderObject();
/*
            sContext.setQuery("in:\"" + tasklist.getRootRelativePath() + "\"");

            sContext.setBackTo(I18nUtil.getLocalizedMessage(pageContext, "backToFolder", new Object[] {tasklist.getName()}));
            sContext.setShortBackTo(tasklist.getName());

            sContext.setFolder(new ZFolderBean(tasklist));
            sContext.setTitle(tasklist.getName());
            sContext.setSelectedId(tasklist.getId());
*/

//            params = new ZSearchParams(sContext.getQuery());
            params = new ZSearchParams("in:\"" + tasklist.getRootRelativePath() + "\"");
            params.setOffset(0);
            params.setLimit(DEFAULT_TASKS_LIMIT);
            params.setSortBy(mSortBy);
            params.setTypes(mTypes);

            sContext.setParams(params);
            sContext.doSearch(mailbox, false, false);

//            pageContext.setAttribute(mVar, sContext.getSearchResult().getHits(), PageContext.REQUEST_SCOPE);
            pageContext.setAttribute(mVar, sContext, PageContext.REQUEST_SCOPE);
    }
}
