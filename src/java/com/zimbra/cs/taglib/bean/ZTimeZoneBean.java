// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.bean;

import com.zimbra.common.calendar.TZIDMapper;

public class ZTimeZoneBean {

    private TZIDMapper.TZ mTz;

    public ZTimeZoneBean(TZIDMapper.TZ tz) {
        mTz = tz;
    }

    public String getId() {
        return mTz.getID();
    }
}
