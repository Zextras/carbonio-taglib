// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.bean;

import com.zimbra.client.ZPhone;
import com.zimbra.client.ZSelectiveCallRejection;

import java.util.ArrayList;
import java.util.List;

public class ZSelectiveCallRejectionBean extends ZCallFeatureBean {

    public ZSelectiveCallRejectionBean(ZSelectiveCallRejection feature) {
        super(feature);
    }

    public List<String> getRejectFrom() {
        List<String> data = getFeature().getRejectFrom();
        List<String> result = new ArrayList<String>(data.size());
        for (String name : data) {
            result.add(ZPhone.getDisplay(name));
        }
        return result;
    }

    public void setRejectFrom(List<String> list) {
        List<String> names = new ArrayList<String>(list.size());
        for (String display : list) {
            names.add(ZPhone.getNonFullName(display));
        }
        getFeature().setRejectFrom(names);
    }

    protected ZSelectiveCallRejection getFeature() {
        return (ZSelectiveCallRejection) super.getFeature(); 
    }
}
