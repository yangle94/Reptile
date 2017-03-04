package com.zs5s.util.empty;

import java.util.Map;

/**
 * Map工具类
 * Created by Angle on 2017/3/4.
 */
public class MapUtil {

    public static <K,V>boolean isEmpty(Map<K,V> map) {
        return map == null || map.size() == 0;
    }

    public static <K,V>boolean isNotEmpty(Map<K,V> map) {
        return map != null && map.size() > 0;
    }

}
