// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.bean;

import com.zimbra.client.ZCallFeature;

public class ZCallFeatureBean {
    private ZCallFeature mFeature;

    public ZCallFeatureBean(ZCallFeature feature) {
        mFeature = feature;
    }

    public void setIsActive(boolean isActive) {
        mFeature.setIsActive(isActive);
    }

    public boolean getIsActive() {
		return mFeature.getIsActive();
	}

    public boolean getIsSubscribed() {
		return mFeature.getIsSubscribed();
	}

    public String getName() {
		return mFeature.getName();
	}

    protected ZCallFeature getFeature() {
        return mFeature;
    }
}
