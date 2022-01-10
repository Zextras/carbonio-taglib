// SPDX-FileCopyrightText: 2022 Synacor, Inc.
// SPDX-FileCopyrightText: 2022 Zextras <https://www.zextras.com>
//
// SPDX-License-Identifier: GPL-2.0-only

package com.zimbra.cs.taglib.memcached;

import com.zimbra.common.service.ServiceException;
import com.zimbra.common.util.memcached.MemcachedMap;
import com.zimbra.common.util.memcached.MemcachedSerializer;
import com.zimbra.common.util.memcached.ZimbraMemcachedClient;
import com.zimbra.cs.taglib.ZJspSession;

public class RouteCache {

    private static RouteCache sTheInstance = new RouteCache();
    private MemcachedMap<RouteCacheKey, String> mMemcachedLookup;

    public static RouteCache getInstance() { return sTheInstance; }

    RouteCache() {
        ZimbraMemcachedClient memcachedClient = MemcachedConnector.getClient();
        RouteSerializer serializer = new RouteSerializer();
        mMemcachedLookup = new MemcachedMap<RouteCacheKey, String>(memcachedClient, serializer, false);
    }

    private static class RouteSerializer implements MemcachedSerializer<String> {
        @Override
        public Object serialize(String value) {
            return value;
        }

        @Override
        public String deserialize(Object obj) throws ServiceException {
            return (String) obj;
        }
    }

    public String get(String accountId) throws ServiceException {
        RouteCacheKey key = new RouteCacheKey(ZJspSession.isProtocolModeHttps() ?  "https" : "http", accountId);
        return mMemcachedLookup.get(key);
    }

    public void put(String accountId, String route) throws ServiceException {
        RouteCacheKey key = new RouteCacheKey(ZJspSession.isProtocolModeHttps() ?  "https" : "http", accountId);
        mMemcachedLookup.put(key, route);
    }
}
