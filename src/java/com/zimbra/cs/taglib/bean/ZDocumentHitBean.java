// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.bean;

import com.zimbra.client.ZDocumentHit;
import com.zimbra.client.ZDocument;
import java.util.Date;

public class ZDocumentHitBean extends ZSearchHitBean {

    private final ZDocumentHit mHit;

    public ZDocumentHitBean(ZDocumentHit hit) {
        super(hit, HitType.briefcase);
        mHit = hit;
    }

    public ZDocument getDocument() {
        return mHit.getDocument();
    }

    public String getDocId() {
        return mHit.getId();
    }

    public String getDocSortField() {
        return mHit.getSortField();
    }

    public Date getCreatedDate() {
        return new Date(mHit.getDocument().getCreatedDate());
    }

    public Date getModifiedDate() {
        return new Date(mHit.getDocument().getModifiedDate());
    }

    public Date getMetaDataChangedDate() {
        return new Date(mHit.getDocument().getMetaDataChangedDate());
    }

}
