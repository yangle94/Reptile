package com.zs5s.util.empty;

import java.util.Collection;

/**
 * List、Set等工具类
 * Created by Angle on 2017/3/4.
 */
public class CollectionUtil {

    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.size() == 0;
    }

    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return collection != null && collection.size() > 0;
    }

    private CollectionUtil() {
        throw new AssertionError("CollectionUtil不允许被实例化");
    }
}
