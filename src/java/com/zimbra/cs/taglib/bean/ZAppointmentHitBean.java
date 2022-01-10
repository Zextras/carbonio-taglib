// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.bean;

import com.zimbra.client.ZAppointmentHit;

public class ZAppointmentHitBean extends ZSearchHitBean {

    private ZAppointmentHit mHit;

    public ZAppointmentHitBean(ZAppointmentHit hit) {
        super(hit, HitType.appointment);
        mHit = hit;
    }
    public ZAppointmentHit getAppointment() {
        return mHit;
    }

    public String getDocId() {
        return mHit.getId();
    }

    public String getDocSortField() {
        return mHit.getSortField();
    }
}
