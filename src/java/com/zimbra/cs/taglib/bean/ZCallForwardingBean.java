// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.bean;

import com.zimbra.client.ZCallFeature;
import com.zimbra.client.ZPhone;
import com.zimbra.common.soap.VoiceConstants;
import com.zimbra.common.service.ServiceException;

public class ZCallForwardingBean extends ZCallFeatureBean {
    public ZCallForwardingBean(ZCallFeature feature) {
        super(feature);
    }

    public void setForwardTo(String phone) {
        String name = ZPhone.getNonFullName(phone);
        getFeature().setData(VoiceConstants.A_FORWARD_TO, name);
    }
    
    public int getNumberOfRings() {
	String rings = getFeature().getData(VoiceConstants.A_NUM_RING_CYCLES);
	try {
	    return Integer.parseInt(rings);
	} catch (NumberFormatException ex) {
	    return -1;
	}
    }
    
    public void setNumberOfRings(int rings) {
	getFeature().setData(VoiceConstants.A_NUM_RING_CYCLES, Integer.toString(rings));
    }

    public String getForwardTo() throws ServiceException {
		String name = getFeature().getData(VoiceConstants.A_FORWARD_TO);
		if (name == null) {
			name = "";
		}
		return ZPhone.getDisplay(name);
    }
}
