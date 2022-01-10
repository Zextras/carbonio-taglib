// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.bean;

import com.zimbra.client.ZCallHit;
import com.zimbra.client.ZPhone;

import java.util.Date;

public class ZCallHitBean extends ZSearchHitBean {

    private ZCallHit mHit;

    public ZCallHitBean(ZCallHit hit) {
        super(hit, HitType.call);
        mHit = hit;
    }

    public String toString() { return mHit.toString(); }

    public ZPhone getCaller() { return mHit.getCaller(); }

    public ZPhone getRecipient() { return mHit.getRecipient(); }

    public String getDisplayCaller() { return mHit.getDisplayCaller(); }

	public String getDisplayRecipient() { return mHit.getDisplayRecipient(); }

    public Date getDate() { return new Date(mHit.getDate()); }

    public long getDuration() { return mHit.getDuration(); }

}
