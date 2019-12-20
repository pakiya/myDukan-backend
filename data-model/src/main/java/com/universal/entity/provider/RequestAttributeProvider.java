package com.universal.entity.provider;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Maps;
import com.universal.utils.Util;

import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestAttributeProvider {
    private static ThreadLocal<Map<String, Object>> requestAttributeHolder = new ThreadLocal<Map<String, Object>>();

    @VisibleForTesting
    public static void emptySet() {
        requestAttributeHolder.set(Maps.newHashMap());
    }

    public static void set(HttpServletRequestWrapper request) {
        Map<String, Object> headerMap = new HashMap<String, Object>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerKey = headerNames.nextElement();
            headerMap.put(headerKey.toUpperCase(), request.getHeader(headerKey));
        }
        requestAttributeHolder.set(headerMap);
    }

    public static Map<String,Object> get() {
        return requestAttributeHolder.get();
    }

    public static Object getValue(String headerName) {
        if (requestAttributeHolder.get() == null || Util.isNullOrEmpty(headerName)) {
            return null;
        }
        return requestAttributeHolder.get().get(headerName.toUpperCase());
    }

    public static void put(String key, Object value) {
        if (requestAttributeHolder.get() == null) {
            throw new IllegalArgumentException("Request Attribute Holder is not initialized");
        }
        if (Util.isNullOrEmpty(key)) {
            throw new IllegalArgumentException("Invalid key");
        }
        requestAttributeHolder.get().put(key.toUpperCase(), value);
    }

    public static void reset() {
        requestAttributeHolder.remove();
    }
}
