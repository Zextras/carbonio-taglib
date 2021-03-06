// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.bean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;

import com.zimbra.common.service.ServiceException;
import com.zimbra.common.soap.Element;
import com.zimbra.common.soap.SoapFaultException;
import com.zimbra.common.soap.ZimbraNamespace;
import com.zimbra.common.util.ExceptionToString;
import com.zimbra.common.util.ZimbraLog;
import com.zimbra.cs.account.AccountServiceException;

public class ZExceptionBean {

    private ServiceException exception;

    public static class Argument {
        private String name, type, val;

        Argument(String name,String type,String val){
            this.type = type;
            this.name = name;
            this.val = val;
        }

        public String getName(){
            return this.name;
        }

        public String getType(){
            return this.type;
        }

        public String getVal(){
            return this.val;
        }
    }

    public ZExceptionBean(Throwable e) {
        if (e instanceof JspException) {
            while ((e instanceof JspException) && (((JspException) e).getRootCause() != null)) {
                e =  ((JspException) e).getRootCause();
            }
        }
        if ((!(e instanceof ServiceException)) && (e.getCause() instanceof ServiceException)) {
            e = e.getCause();
        }
        if (e instanceof SoapFaultException)  {
            String code = ((SoapFaultException) e).getCode();
            if(code != null && (code.equals(ServiceException.AUTH_EXPIRED) || code.equals(ServiceException.AUTH_REQUIRED) || code.equals(AccountServiceException.AUTH_FAILED))) {
                ZimbraLog.webclient.debug(e.getMessage(), e);
            } else {
                ZimbraLog.webclient.warn(e.getMessage(), e);
            }
        } else if (!(e instanceof SkipPageException)) {
            ZimbraLog.webclient.warn(e.getMessage(), e);
        }

        if (e instanceof ServiceException) {
            exception = (ServiceException) e;
        } else {
            exception = ZTagLibException.TAG_EXCEPTION(e.getMessage(), e);
        }
    }

    public Exception getException() {
        return exception;
    }

    public String getCode() {
        return exception.getCode();
    }

    public String getId() {
        return exception.getId();
    }

    public String getStackStrace() {
       return ExceptionToString.ToString(exception);
    }

    public List<Argument> getArguments(){
        List<Argument> args = new ArrayList<Argument>();
        try {
            if (exception instanceof SoapFaultException) {
                SoapFaultException sfe = (SoapFaultException) exception;
                Element d = sfe.getDetail();
                if (d != null) {
                    List<Element> list = d.getPathElementList(
                            new String[] {ZimbraNamespace.E_ERROR.getName(), ZimbraNamespace.E_ARGUMENT.getName()});
                    for (Element el : list) {
                        args.add(new Argument(el.getAttribute(ZimbraNamespace.A_ARG_NAME,""),
                            el.getAttribute(ZimbraNamespace.A_ARG_TYPE, ""), el.getText()));
                    }
                }
            }
        } catch (Exception e) {
            //ignore...
        }
        return args;
    }
}
