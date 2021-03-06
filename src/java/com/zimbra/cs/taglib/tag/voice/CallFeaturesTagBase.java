// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.voice;

import com.zimbra.cs.taglib.tag.ZimbraSimpleTag;

import java.util.List;
import java.util.ArrayList;

abstract public class CallFeaturesTagBase extends ZimbraSimpleTag {
	protected String mVar;
	protected String mPhone;
	protected Boolean mEmailNotificationActive;
	protected String mEmailNotificationAddress;
	
	protected String mAddEmailNotificationaddress;
	protected String mRemoveEmailNotificationaddress;
	
	protected Boolean mCallForwardingActive;
	protected String mCallForwardingForwardTo;
	protected Boolean mSelectiveCallForwardingActive;
	protected String mSelectiveCallForwardingForwardTo;
	protected List<String> mSelectiveCallForwardingForwardFrom;
	
	protected Boolean mAnonymousCallRejectionActive;
	protected Boolean mSelectiveCallRejectionActive;
	protected List<String> mSelectiveCallRejectionRejectFrom;
	
	protected Long mNumberPerPage;
	protected Integer mNumberOfRings;
	
	protected Boolean mPlayDateAndTimeInMsgEnv;
	protected Boolean mPlayCallerNameInMsgEnv;
	protected Boolean mAutoPlayNewMsgs;
	protected Boolean mSkipPinEntry;
	protected String mPromptLevel;
	protected String mAnsweringLocale;
	protected String mUserLocale;

	protected Boolean mEmailNotifTrans;
	protected Boolean mEmailNotifAttach;

	public void setVar(String var) { mVar = var; }
	public void setPhone(String phone) { mPhone = phone; }
	public void setEmailnotificationactive(String active) { mEmailNotificationActive = booleanValue(active); }
	public void setEmailnotificationaddress(String address) { mEmailNotificationAddress = address.trim(); }
	
	public void setAddEmailnotificationaddress(String address) { mAddEmailNotificationaddress = address.trim(); }
	public void setRemoveEmailnotificationaddress(String address) { mRemoveEmailNotificationaddress = address.trim(); }
	
	public void setCallforwardingactive(String active) { mCallForwardingActive = booleanValue(active); }
	public void setCallforwardingforwardto(String number) { mCallForwardingForwardTo = number.trim(); }
	public void setSelectivecallforwardingactive(String active) { mSelectiveCallForwardingActive = booleanValue(active); }
	public void setSelectivecallforwardingforwardto(String number) { mSelectiveCallForwardingForwardTo = number.trim(); }
	public void setSelectivecallforwardingforwardfrom(String[] numbers) {
		mSelectiveCallForwardingForwardFrom = new ArrayList<String>(numbers.length);
		for (String number : numbers) {
			mSelectiveCallForwardingForwardFrom.add(number.trim());
		}
	}
	public void setAnonymouscallrejectionactive(String active) { mAnonymousCallRejectionActive = booleanValue(active); }
	public void setSelectivecallrejectionactive(String active) { mSelectiveCallRejectionActive = booleanValue(active); }
	public void setSelectivecallrejectionrejectfrom(String[] numbers) {
		mSelectiveCallRejectionRejectFrom = new ArrayList<String>(numbers.length);
		for (String number : numbers) {
			mSelectiveCallRejectionRejectFrom.add(number.trim());
		}
	}
	
	public void setNumberPerPage(String number) {
	    try {
		mNumberPerPage = Long.decode(number);
	    } catch (NumberFormatException ex) {
		mNumberPerPage = null;
	    }
	}
	public void setNumberOfRings(String rings) {
	    try {
		mNumberOfRings = Integer.decode(rings);
	    } catch (NumberFormatException ex) {
		mNumberOfRings = null;
	    }
	}
	
	public void setPromptLevel(String level) { mPromptLevel = level; }
	public void setAnsweringLocale(String locale) { mAnsweringLocale = locale; }
	public void setUserLocale(String locale) { mUserLocale = locale; }
	public void setPlayDateAndTimeInMsgEnv(String play) { mPlayDateAndTimeInMsgEnv = booleanValue(play); }
	public void setPlayCallerNameInMsgEnv(String play) { mPlayCallerNameInMsgEnv = booleanValue(play); }
	public void setAutoPlayNewMsgs(String play) { mAutoPlayNewMsgs = booleanValue(play); }
	public void setSkipPinEntry(String skip) { mSkipPinEntry = booleanValue(skip); }
	public void setRequirePinEntry(String require) { mSkipPinEntry = !booleanValue(require); }

	public void setEmailNotifTrans(String notif) { mEmailNotifTrans = booleanValue(notif); }
	public void setEmailNotifAttach(String notif) { mEmailNotifAttach = booleanValue(notif); }

	private Boolean booleanValue(String value) {
		return Boolean.valueOf("TRUE".equalsIgnoreCase(value) || "on".equalsIgnoreCase(value));
	}
}
