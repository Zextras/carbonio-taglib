// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.bean;

import com.zimbra.client.ZAppointmentHit;

import java.util.Collections;
import java.util.List;

public class ZApptSummariesBean {

    private List<ZAppointmentHit> mAppts;

    public ZApptSummariesBean(List<ZAppointmentHit> appts) {
        mAppts = appts;
        Collections.sort(mAppts, new ZAppointmentHit.SortByTimeDurationFolder());
    }

    public int getSize() { return mAppts.size(); }

    public List<ZAppointmentHit> getAppointments() {
        return mAppts;
    }
}
