// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.bean;

public class NextPrevItemBean {
    private int mPrevIndex;
    private int mPrevOffset;
    private int mNextIndex;
    private int mNextOffset;
    private boolean mHasNext;
    private boolean mHasPrev;

    public NextPrevItemBean(boolean hasPrev, int prevIndex, int prevOffset, boolean hasNext, int nextIndex, int nextOffset) {
        mHasPrev = hasPrev;
        mPrevIndex = prevIndex;
        mPrevOffset = prevOffset;
        mHasNext = hasNext;
        mNextIndex = nextIndex;
        mNextOffset = nextOffset;
    }

    public boolean getHasPrev() {
        return mHasPrev;
    }

    public int getPrevIndex() {
        return mPrevIndex;
    }

    public int getPrevOffset() {
        return mPrevOffset;
    }

    public boolean getHasNext() {
        return mHasNext;
    }
    
    public int getNextIndex() {
        return mNextIndex;
    }

    public int getNextOffset() {
        return mNextOffset;
    }
}
