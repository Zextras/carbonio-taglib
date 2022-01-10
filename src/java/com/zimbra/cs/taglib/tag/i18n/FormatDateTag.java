// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.tag.i18n;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class FormatDateTag extends SimpleTagSupport  {

	//
	// Data
	//

	protected Date value;
	protected String type = I18nUtil.DEFAULT_DATE_TYPE_NAME;
	protected String dateStyle;
	protected String timeStyle;
	protected String pattern;
	protected TimeZone timeZone;
	protected String var;
	protected int scope = I18nUtil.DEFAULT_SCOPE_VALUE;

	//
	// Public methods
	//

	public void setValue(Date value) {
		this.value = value;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDateStyle(String style) {
		this.dateStyle = style;
	}

	public void setTimeStyle(String style) {
		this.timeStyle = style;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public void setTimeZone(Object timeZone) {
		if (timeZone instanceof TimeZone) {
			this.timeZone = (TimeZone)timeZone;
		}
		else {
			this.timeZone = TimeZone.getTimeZone(String.valueOf(timeZone));
		}
	}

	public void setVar(String var) {
		this.var = var;
	}

	public void setScope(String scope) {
		this.scope = I18nUtil.getScope(scope);
	}

	//
	// SimpleTag methods
	//

	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext)getJspContext();

		// create formatter
		Locale locale = I18nUtil.findLocale(pageContext);
		DateFormat formatter = this.pattern != null ? new SimpleDateFormat(this.pattern, locale) : null;
		if (formatter == null) {
			int dateStyle = I18nUtil.getStyle(this.dateStyle);
			int timeStyle = I18nUtil.getStyle(this.timeStyle);
			if (I18nUtil.TYPE_DATE.equalsIgnoreCase(this.type)) {
				formatter = DateFormat.getDateInstance(dateStyle, locale);
			}
			else if (I18nUtil.TYPE_TIME.equalsIgnoreCase(this.type)) {
				formatter = DateFormat.getTimeInstance(timeStyle, locale);
			}
			else {
				formatter = DateFormat.getDateTimeInstance(dateStyle, timeStyle, locale);
			}
		}

		TimeZone tz = this.timeZone;
		if (tz == null) {
			TimeZoneTag timeZoneTag = (TimeZoneTag)findAncestorWithClass(this, TimeZoneTag.class);
			if (timeZoneTag != null) tz = timeZoneTag.getTimeZone();
			if (tz == null) tz = I18nUtil.findTimeZone(pageContext);
		}
		formatter.setTimeZone(tz);

		// format message
		String message = formatter.format(this.value);

		// output string
		if (this.var == null) {
			pageContext.getOut().print(message);
		}

		// save variable
		else {
			pageContext.setAttribute(this.var, message, this.scope);
		}

		// clear state
		this.value = null;
		this.type = I18nUtil.DEFAULT_DATE_TYPE_NAME;
		this.dateStyle = null;
		this.timeStyle = null;
		this.pattern = null;
		this.timeZone = null;
		this.var = null;
		this.scope = I18nUtil.DEFAULT_SCOPE_VALUE;
	}

} // class FormatDateTag