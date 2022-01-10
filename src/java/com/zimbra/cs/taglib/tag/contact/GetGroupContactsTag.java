// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.contact;

import com.zimbra.client.ZContact;
import com.zimbra.client.ZMailbox;
import com.zimbra.common.service.ServiceException;
import com.zimbra.cs.taglib.tag.ZimbraSimpleTag;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import java.io.IOException;
import java.util.Map;

public class GetGroupContactsTag extends ZimbraSimpleTag {

    private String var;
    private String id;
    private boolean json;

    public void setVar(String var) {
        this.var = var;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setJson(boolean json) {
        this.json = json;
    }

    public void doTag() throws JspException, IOException {
        JspContext jctxt = getJspContext();
        try {
            ZMailbox mbox = getMailbox();
            ZContact group = mbox.getContact(id);
            if (json) {
                JSONArray jsonArray = new JSONArray();
                Map<String, ZContact> members = group.getMembers();
                String addr = null;
                for (ZContact contact : members.values()) {
                    if(!contact.isTypeI()) {
                        Map<String, String> attrs = contact.getAttrs();
                        addr = attrs.get("email");
                    }
                    else {
                        addr = contact.getId();
                    }
                    if (addr != null) {
                        jsonArray.put(addr);
                    }
                }
                JSONObject top = new JSONObject();
                top.put("Result", jsonArray);
                top.write(jctxt.getOut());
            }
        } catch (JSONException e) {
            throw new JspTagException(e);
        } catch (ServiceException e) {
            throw new JspTagException(e);
        }
    }
}
