// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.bean;

import com.zimbra.client.ZVoiceMailPrefs;

import com.zimbra.common.soap.VoiceConstants;

public class ZVoiceMailPrefsBean extends ZCallFeatureBean {

    private boolean toBoolean(String v) {
        return (v != null && v.equalsIgnoreCase("true"));
    }

    public ZVoiceMailPrefsBean(ZVoiceMailPrefs feature) {
        super(feature);
    }

    public void set(String key, String value) {
        getFeature().set(key, value);
    }
    public String get(String key) {
        return getFeature().get(key);
    }


    public String getEmailNotificationAddress() {
        return getFeature().getEmailNotificationAddress();
    }

    public void setEmailNotificationAddress(String address) {
        getFeature().setEmailNotificationAddress(address);
    }

    public boolean getPlayDateAndTimeInMsgEnv() {
        return getFeature().getPlayDateAndTimeInMsgEnv();
    }
    
    public void setPlayDateAndTimeInMsgEnv(boolean value) {
        getFeature().setPlayDateAndTimeInMsgEnv(value);
    }
    
    public boolean getAutoPlayNewMsgs() {
        return getFeature().getAutoPlayNewMsgs();
    }
    
    public void setAutoPlayNewMsgs(boolean value) {
        getFeature().setAutoPlayNewMsgs(value);
    }
    
    public String getPromptLevel() {
        return getFeature().getPromptLevel();
    }
    
    public void setPromptLevel(String level) {
        getFeature().setPromptLevel(level);
    }
    
    public boolean getPlayCallerNameInMsgEnv() {
        return getFeature().getPlayCallerNameInMsgEnv();
    }
    
    public void setPlayCallerNameInMsgEnv(boolean value) {
        getFeature().setPlayCallerNameInMsgEnv(value);
    }
    
    public boolean getSkipPinEntry() {
        return getFeature().getSkipPinEntry();
    }
    
    public void setSkipPinEntry(boolean value) {
        getFeature().setSkipPinEntry(value);
    }
    
    public String getUserLocale() {
        return getFeature().getUserLocale();
    }

    public void setUserLocale(String locale) {
        getFeature().setUserLocale(locale);
    }

    public String getAnsweringLocale() {
        return getFeature().getAnsweringLocale();
    }

    public void setAnsweringLocale(String locale) {
        getFeature().setAnsweringLocale(locale);
    }

    public String getGreetingType() {
        return getFeature().getGreetingType();
    }

    public void setGreetingType(String type) {
        getFeature().setGreetingType(type);
    }

    public boolean getEmailNotifStatus() {
        return getFeature().getEmailNotifStatus();
    }
    
    public void setEmailNotifStatus(boolean value) {
        getFeature().setEmailNotifStatus(value);
    }

    public boolean getPlayTutorial() {
        return getFeature().getPlayTutorial();
    }
    
    public void setPlayTutorial(boolean value) {
        getFeature().setPlayTutorial(value);
    }

    public int getVoiceItemsPerPage() {
        return getFeature().getVoiceItemsPerPage();
    }
    
    public void setVoiceItemsPerPage(int value) {
        getFeature().setVoiceItemsPerPage(value);
    }
    
    public boolean getEmailNotifTrans() {
        return getFeature().getEmailNotifTrans();
    }

    public void setEmailNotifTrans(boolean value) {
        getFeature().setEmailNotifTrans(value);
    }
    
    public boolean getEmailNotifAttach() {
        return getFeature().getEmailNotifAttach();
    }

    public void setEmailNotifAttach(boolean value) {
        getFeature().setEmailNotifAttach(value);
    }

    
    protected ZVoiceMailPrefs getFeature() {
        return (ZVoiceMailPrefs) super.getFeature();
    }
}
