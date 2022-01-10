// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag;

import com.zimbra.common.auth.ZAuthToken;
import com.zimbra.common.soap.Element;
import com.zimbra.common.soap.SoapHttpTransport;
import com.zimbra.common.soap.SoapProtocol;
import com.zimbra.common.soap.SoapTransport;
import com.zimbra.common.soap.SoapTransport.DebugListener;

public class TagUtil {

    public static class JsonDebugListener implements DebugListener {
        Element env;
        public void sendSoapMessage(Element envelope) {}
        public void receiveSoapMessage(Element envelope) {env = envelope; }
        public Element getEnvelope(){ return env; }
    }
    
    public static SoapTransport newJsonTransport(String url, String remoteAddr, ZAuthToken authToken, DebugListener debug) {
        return newJsonTransport(url, remoteAddr, authToken, null, debug);
    }

    public static SoapTransport newJsonTransport(String url, String remoteAddr, ZAuthToken authToken, String csrfToken, DebugListener debug) {
        SoapTransport transport = new SoapHttpTransport(url);
        transport.setClientIp(remoteAddr);
        transport.setAuthToken(authToken);
        transport.setCsrfToken(csrfToken);
        transport.setRequestProtocol(SoapProtocol.SoapJS);
        transport.setResponseProtocol(SoapProtocol.SoapJS);
        transport.setDebugListener(debug);
        return transport;
    }
}
