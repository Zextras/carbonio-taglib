// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.i18n;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class ParamTag extends BodyTagSupport {

	//
	// Data
	//

	protected Object value;

	//
	// Public methods
	//

	public void setValue(Object value) {
		this.value = value;
	}

	//
	// TagSupport methods
	//

	public int doEndTag() throws JspException {
		MessageTag messageTag = (MessageTag)findAncestorWithClass(this, MessageTag.class);
		if (messageTag != null) {
			Object value = this.value;
			if (value == null) {
				BodyContent bodyContent = getBodyContent();
				value = I18nUtil.evaluate(pageContext, bodyContent.getString(), Object.class);
			}
			messageTag.addParam(value);
		}
		// clear state
		this.value= null;
		// process page
		return EVAL_PAGE;
	}

} // class ParamTag