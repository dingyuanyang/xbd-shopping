package com.xbd.note.shopping.cache.local;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuanyang
 */
public class LocationCache {

    private static Map<Long, String> CITY_MAP = new HashMap<>();

    static {
        CITY_MAP.put(1L, "北京");
    }

    public static String cityName(Long id) {
        return CITY_MAP.get(id);
    }
}
