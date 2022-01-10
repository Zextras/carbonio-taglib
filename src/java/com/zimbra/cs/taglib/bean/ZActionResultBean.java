// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.bean;

import com.zimbra.client.ZMailbox.ZActionResult;

public class ZActionResultBean {

    private ZActionResult mResult;
    private String[] mIds;
    private int mIdCount = -1;

    public ZActionResultBean(ZActionResult result) {
        mResult = result;
    }

    public synchronized String[] getIds() {
        if (mIds == null) mIds = mResult.getIdsAsArray();
        return mIds;
    }

    public synchronized int getIdCount() {

        if (mIdCount == -1) {
            if (mIds != null) {
                mIdCount = mIds.length;
            } else {
                String ids = mResult.getIds();
                int len = ids.length();
                if (ids == null || len == 0) return 0;
                mIdCount = 1;
                for (int i=0; i < len; i++) {
                    if (ids.charAt(i) == ',') mIdCount++;
                }
            }
        }
        return mIdCount;
    }
}
