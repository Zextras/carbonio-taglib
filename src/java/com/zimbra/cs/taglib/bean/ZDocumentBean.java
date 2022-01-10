// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.bean;

import java.util.Date;

import com.zimbra.common.mime.ContentType;
import com.zimbra.client.ZDocument;

public class ZDocumentBean {

    private final ZDocument mDoc;

    public ZDocumentBean(ZDocument doc) {
        mDoc = doc;
    }

    public Date getCreatedDate() {
        return new Date(mDoc.getCreatedDate());
    }

    public Date getModifiedDate() {
        return new Date(mDoc.getModifiedDate());
    }

    public Date getMetaDataChangedDate() {
        return new Date(mDoc.getMetaDataChangedDate());
    }

    public String getId() {
        return mDoc.getId();
    }

    public String getName() {
        return mDoc.getName();
    }

    public String getFolderId() {
        return mDoc.getFolderId();
    }

    public String getVersion() {
        return mDoc.getVersion();
    }

    public String getEditor() {
        return mDoc.getEditor();
    }

    public String getCreator() {
        return mDoc.getCreator();
    }

    public String getRestUrl() {
        return mDoc.getRestUrl();
    }

    public boolean isWiki() {
        return mDoc.isWiki();
    }

    public String getContentType() {
        String contentType = mDoc.getContentType();
        return new ContentType(contentType).getContentType();
    }

    public long getSize() {
        return mDoc.getSize();
    }

    public String getTagIds() {
        return mDoc.getTagIds();
    }

}