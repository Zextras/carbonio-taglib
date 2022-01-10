// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.bean;

import java.util.List;

public class ZApptMultiDayLayoutBean {

    private List<ZApptRowLayoutBean> mAllDayRows;
    private List<ZApptRowLayoutBean> mRows;
    private List<ZApptDayLayoutBean> mDays;
    private List<List<ZApptRowLayoutBean>> mRowsSeperatedByDays;
    private List<List<ZApptRowLayoutBean>> mAllDayRowsSeperatedByDays;
    private int mMaxColumns;

    public ZApptMultiDayLayoutBean(List<ZApptDayLayoutBean> days, List<ZApptRowLayoutBean> allDayRows, List<ZApptRowLayoutBean> rows, List<List<ZApptRowLayoutBean>> rowsSeperatedByDays, List<List<ZApptRowLayoutBean>> allDayRowsSeperatedByDays) {
        mAllDayRows = allDayRows;
        mRows = rows;
        mDays = days;
        mMaxColumns = 0;
        mRowsSeperatedByDays = rowsSeperatedByDays;
        mAllDayRowsSeperatedByDays = allDayRowsSeperatedByDays;
        for (ZApptDayLayoutBean day : days) {
            mMaxColumns += day.getColumns().size();
        }
    }

    public List<ZApptRowLayoutBean> getAllDayRows() {
        return mAllDayRows;
    }

    public List<ZApptRowLayoutBean> getRows() {
        return mRows;
    }

    public List<List<ZApptRowLayoutBean>> getRowsSeperatedByDays() {
        return mRowsSeperatedByDays;
    }

    public List<List<ZApptRowLayoutBean>> getAllDayRowsSeperatedByDays() {
        return mAllDayRowsSeperatedByDays;
    }

    public List<ZApptDayLayoutBean> getDays() {
        return mDays;
    }

    public int getNumDays() {
        return mDays.size();
    }

    public int getMaxColumns() {
        return mMaxColumns;
    }

    public long getScheduleAlldayOverlapCount() {
        int overlap = 0;
        for ( ZApptDayLayoutBean day : mDays) {
            if (!day.getAllDayAppts().isEmpty())
                overlap++;
        }
        return overlap;
    }
}
