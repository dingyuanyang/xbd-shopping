package com.xbd.note.shopping.cache.local;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuanyang
 */
public class BrandCache {

    private static Map<Long, String> BRAND_MAP = new HashMap<>();

    static {
        BRAND_MAP.put(1L, "iphone");
    }

    public static String getBrandName(Long id) {
        return BRAND_MAP.get(id);
    }
}
