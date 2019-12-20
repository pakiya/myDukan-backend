package com.universal.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collection;

public class Util {

    private static ObjectMapper objectMapper;


    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    @SuppressWarnings("unchecked")
    public static <T> T convertStringToObject(String objectStr, Class t) {
        if (isNullOrEmpty(objectStr))
            return null;
        try {
            return (T) objectMapper.readValue(objectStr.getBytes(), t);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static <T> String convertObjectToString(T obj) {

        String response = null;
        try {
            if (obj == null)
                return null;

            response = objectMapper.writeValueAsString(obj);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return response;
    }

    public static final String generateUniqueId(String prefix, int length) {
        return UniqueIdGenerators.ALPHANUMERIC_WITH_TIME_MILLI.generate(prefix, length);
    }

    public static boolean isCollectionNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}
