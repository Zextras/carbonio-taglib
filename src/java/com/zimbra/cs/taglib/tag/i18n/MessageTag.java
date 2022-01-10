// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.i18n;

import java.io.*;
import java.util.*;
import java.text.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class MessageTag extends BodyTagSupport {

	//
	// Data
	//

	protected String pattern;
	protected ResourceBundle bundle;
	protected String key;
	protected String var;
	protected int scope = PageContext.PAGE_SCOPE;
	protected List<Object> params;

	//
	// Public methods
	//

	public void addParam(Object value) {
		this.params.add(value);
	}

	public Object[] getParams() {
		return this.params.toArray(new Object[]{});
	}

	// properties

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public void setScope(String scope) {
		this.scope = I18nUtil.getScope(scope);
	}

	//
	// TagSupport methods
	//

	public int doStartTag() throws JspException {
		this.params = new LinkedList<Object>();
		return EVAL_BODY_BUFFERED;
	}

	public int doEndTag() throws JspException {
		String pattern = this.pattern;
		ResourceBundle bundle = null;

		// get pattern from bundle
		if (pattern == null) {
			String prefix = null;

			bundle = this.bundle;
			if (bundle == null) {
				BundleTag bundleTag = (BundleTag)findAncestorWithClass(this, BundleTag.class);
				if (bundleTag != null) {
					bundle = bundleTag.getBundle();
					prefix = bundleTag.getPrefix();
				}
			}
			if (bundle == null) {
				bundle = I18nUtil.findBundle(pageContext);
			}

			// get message
			try {
				pattern = bundle.getString(prefix != null ? prefix+this.key : this.key);
			}
			catch (Exception e) {
				pattern = "???"+this.key+"???";
			}
		}

		// format message
		String message = pattern;
		if ((this.pattern != null || bundle != null) && this.params.size() != 0) {
			// set timezone on formatters
			TimeZone timeZone = null;
			TimeZoneTag timeZoneTag = (TimeZoneTag)findAncestorWithClass(this, TimeZoneTag.class);
			if (timeZoneTag != null) timeZone = timeZoneTag.getTimeZone();
			if (timeZone == null) timeZone = I18nUtil.findTimeZone(pageContext);

			MessageFormat formatter = new MessageFormat(pattern, I18nUtil.findLocale(pageContext));
			for (Format format : formatter.getFormatsByArgumentIndex()) {
				if (format != null && format instanceof DateFormat) {
					((DateFormat)format).setTimeZone(timeZone);
				}
			}

			// now format!
			message = formatter.format(getParams());
		}

		// output text
		if (this.var == null) {
			try {
				pageContext.getOut().print(message);
			}
			catch (IOException e) {
				throw new JspException(e);
			}
		}

		// store result
		else {
			pageContext.setAttribute(this.var, message, this.scope);
		}

		// clear old state
		this.pattern = null;
		this.bundle = null;
		this.key = null;
		this.var = null;
		this.scope = PageContext.PAGE_SCOPE;

		// continue with page
		return EVAL_PAGE;
	}

} // class MessageTag