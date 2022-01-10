// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.bean;

import com.zimbra.client.ZPhone;
import com.zimbra.client.ZSelectiveCallForwarding;

import java.util.ArrayList;
import java.util.List;

public class ZSelectiveCallForwardingBean extends ZCallForwardingBean {

    public ZSelectiveCallForwardingBean(ZSelectiveCallForwarding feature) {
        super(feature);
    }

    public List<String> getForwardFrom() {
        List<String> data = getFeature().getForwardFrom();
        List<String> result = new ArrayList<String>(data.size());
        for (String name : data) {
            result.add(ZPhone.getDisplay(name));
        }
        return result;
    }

    public void setForwardFrom(List<String> list) {
        List<String> names = new ArrayList<String>(list.size());
        for (String display : list) {
            names.add(ZPhone.getNonFullName(display));
        }
        getFeature().setForwardFrom(names);
    }

    protected ZSelectiveCallForwarding getFeature() {
        return (ZSelectiveCallForwarding) super.getFeature(); 
    }
}
