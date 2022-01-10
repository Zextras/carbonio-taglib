// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.memcached;

import com.zimbra.common.util.memcached.MemcachedKey;

public class RouteCacheKey implements MemcachedKey {
    private String mKeyStr;
    private String ROUTE_TAGS= "route:proto=";

    public RouteCacheKey(String protocolMode, String accountId) {
        mKeyStr = protocolMode + ";id=" + accountId;
    }

    public boolean equals(Object other) {
        if (other instanceof RouteCacheKey) {
            RouteCacheKey otherKey = (RouteCacheKey) other;
            return mKeyStr.equals(otherKey.mKeyStr);
        }
        return false;
    }

    public int hashCode() {
        return mKeyStr.hashCode();
    }

    // MemcachedKey interface
    public String getKeyPrefix() { return ROUTE_TAGS; }
    public String getKeyValue() { return mKeyStr; }
}