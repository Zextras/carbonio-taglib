// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.bean;

import com.zimbra.client.ZPhoneAccount;
import com.zimbra.client.ZPhone;
import com.zimbra.common.service.ServiceException;

public class ZPhoneAccountBean {

    private ZPhoneAccount account;

    public ZPhoneAccountBean(ZPhoneAccount account) {
        this.account = account;
    }

    public ZFolderBean getRootFolder() {
        return new ZFolderBean(account.getRootFolder());
    }

    public ZPhone getPhone() {
        return account.getPhone();
    }

    public ZCallFeaturesBean getCallFeatures() throws ServiceException {
        return new ZCallFeaturesBean(account.getCallFeatures(), false);
    }

	public boolean getHasVoiceMail() {
		return account.getHasVoiceMail();
	}

    public String getPhoneType() {
        return account.getPhoneType();
    }

}
