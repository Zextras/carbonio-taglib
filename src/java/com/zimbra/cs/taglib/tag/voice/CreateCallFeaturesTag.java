// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.voice;

import com.zimbra.common.service.ServiceException;
import com.zimbra.cs.taglib.bean.ZCallFeaturesBean;
import com.zimbra.cs.taglib.bean.ZCallForwardingBean;
import com.zimbra.cs.taglib.bean.ZSelectiveCallForwardingBean;
import com.zimbra.client.ZCallFeatures;
import com.zimbra.client.ZMailbox;
import com.zimbra.client.ZPhoneAccount;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;

public class CreateCallFeaturesTag extends CallFeaturesTagBase {
    public void doTag() throws JspException, IOException {
        try {
            ZMailbox mailbox = getMailbox();
            ZPhoneAccount account = mailbox.getPhoneAccount(mPhone);
            ZCallFeaturesBean newFeatures = new ZCallFeaturesBean(new ZCallFeatures(mailbox, account.getPhone()), true);

            String address = mEmailNotificationActive ? mEmailNotificationAddress : "";
            newFeatures.getVoiceMailPrefs().setEmailNotificationAddress(address);

	    ZCallForwardingBean newCallForwarding = newFeatures.getCallForwardingAll();
	    newCallForwarding.setIsActive(mCallForwardingActive);
	    newCallForwarding.setForwardTo(mCallForwardingForwardTo);

            ZSelectiveCallForwardingBean newSelectiveCallForwarding = newFeatures.getSelectiveCallForwarding();
            newSelectiveCallForwarding.setIsActive(mSelectiveCallForwardingActive);
            newSelectiveCallForwarding.setForwardTo(mSelectiveCallForwardingForwardTo);
            newSelectiveCallForwarding.setForwardFrom(mSelectiveCallForwardingForwardFrom);

	    // Uncomment when selective call rejection has been implemented in the soap interface
	    //ZSelectiveCallRejectionBean newSelectiveCallRejection = newFeatures.getSelectiveCallRejection();

	    newFeatures.getCallForwardingAll().setNumberOfRings(mNumberOfRings);

	    newFeatures.getVoiceMailPrefs().setAutoPlayNewMsgs(mAutoPlayNewMsgs);
	    newFeatures.getVoiceMailPrefs().setPlayDateAndTimeInMsgEnv(mPlayDateAndTimeInMsgEnv);
	    newFeatures.getVoiceMailPrefs().setSkipPinEntry(mSkipPinEntry);
	    newFeatures.getVoiceMailPrefs().setPlayCallerNameInMsgEnv(mPlayCallerNameInMsgEnv);
	    newFeatures.getVoiceMailPrefs().setPromptLevel(mPromptLevel);
	    newFeatures.getVoiceMailPrefs().setAnsweringLocale(mAnsweringLocale);
	    newFeatures.getVoiceMailPrefs().setUserLocale(mUserLocale);

	    newFeatures.getVoiceMailPrefs().setEmailNotifTrans(mEmailNotifTrans);
	    newFeatures.getVoiceMailPrefs().setEmailNotifAttach(mEmailNotifAttach);

            getJspContext().setAttribute(mVar, newFeatures, PageContext.PAGE_SCOPE);
        } catch (ServiceException e) {
            throw new JspTagException(e);
        }
    }
}
