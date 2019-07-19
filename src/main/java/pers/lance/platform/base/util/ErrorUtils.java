package pers.lance.platform.base.util;


import pers.lance.platform.base.bean.CustomResultCodeEnum;
import pers.lance.platform.base.bean.CustomBusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * 异常错误抛出工具
 *
 * @author lance
 * @date 2018-05-06
 */
public class ErrorUtils {

    /**
     * 对象为空时
     *
     * @param obj
     * @param message
     */
    public static void isNull(Object obj, String message) {
        if (Objects.isNull(obj)) {
            message(message);
        }
    }

    /**
     * 对象为空时
     *
     * @param obj
     * @param codeEnum
     */
    public static void isNull(Object obj, CustomResultCodeEnum codeEnum) {
        if (Objects.isNull(obj)) {
            message(codeEnum);
        }
    }

    /**
     * 对象为空时
     *
     * @param obj
     */
    public static void isNull(Object obj) {
        if (Objects.isNull(obj)) {
            message();
        }
    }

    /**
     * 对象为空时
     *
     * @param obj
     * @param message
     */
    public static void nonNull(Object obj, String message) {
        if (Objects.nonNull(obj)) {
            message(message);
        }
    }

    /**
     * 集合为空时
     *
     * @param list
     * @param message
     */
    public static void isEmpty(List list, String message) {
        if (CollectionUtils.isEmpty(list)) {
            message(message);
        }
    }

    /**
     * 对象不为空时
     *
     * @param obj
     * @param codeEnum
     */
    public static void nonNull(Object obj, CustomResultCodeEnum codeEnum) {
        if (Objects.nonNull(obj)) {
            message(codeEnum);
        }
    }

    /**
     * 对象不为空时
     *
     * @param obj
     */
    public static void nonNull(Object obj) {
        if (Objects.nonNull(obj)) {
            message();
        }
    }


    /**
     * 对象相等时
     *
     * @param val1
     * @param val2
     * @param message
     */
    public static void equals(Object val1, Object val2, String message) {
        if (Objects.equals(val1, val2)) {
            message(message);
        }
    }

    /**
     * 对象相等时
     *
     * @param val1
     * @param val2
     * @param codeEnum
     */
    public static void equals(Object val1, Object val2, CustomResultCodeEnum codeEnum) {
        if (Objects.equals(val1, val2)) {
            message(codeEnum);
        }
    }

    /**
     * 对象相等时
     *
     * @param val1
     * @param val2
     */
    public static void equals(Object val1, Object val2) {
        if (Objects.equals(val1, val2)) {
            message();
        }
    }

    /**
     * 对象不相等时
     *
     * @param val1
     * @param val2
     * @param message
     */
    public static void unequals(Object val1, Object val2, String message) {
        if (!Objects.equals(val1, val2)) {
            message(message);
        }
    }

    /**
     * 对象不相等时
     *
     * @param val1
     * @param val2
     * @param codeEnum
     */
    public static void unequals(Object val1, Object val2, CustomResultCodeEnum codeEnum) {
        if (!Objects.equals(val1, val2)) {
            message(codeEnum);
        }
    }

    /**
     * 对象不相等时
     *
     * @param val1
     * @param val2
     */
    public static void unequals(Object val1, Object val2) {
        if (!Objects.equals(val1, val2)) {
            message();
        }
    }

    /**
     * 字符串相等时
     *
     * @param val1
     * @param val2
     * @param message
     */
    public static void equals(String val1, String val2, String message) {
        if (StringUtils.equals(val1, val2)) {
            message(message);
        }
    }

    /**
     * 字符串相等时
     *
     * @param val1
     * @param val2
     * @param codeEnum
     */
    public static void equals(String val1, String val2, CustomResultCodeEnum codeEnum) {
        if (StringUtils.equals(val1, val2)) {
            message(codeEnum);
        }
    }

    /**
     * 字符串相等时
     *
     * @param val1
     * @param val2
     */
    public static void equals(String val1, String val2) {
        if (StringUtils.equals(val1, val2)) {
            message();
        }
    }

    /**
     * 字符串不相等时
     *
     * @param val1
     * @param val2
     * @param message
     */
    public static void unequals(String val1, String val2, String message) {
        if (!StringUtils.equals(val1, val2)) {
            message(message);
        }
    }

    /**
     * 字符串不相等时
     *
     * @param val1
     * @param val2
     * @param codeEnum
     */
    public static void unequals(String val1, String val2, CustomResultCodeEnum codeEnum) {
        if (!StringUtils.equals(val1, val2)) {
            message(codeEnum);
        }
    }

    /**
     * 字符串不相等时
     *
     * @param val1
     * @param val2
     */
    public static void unequals(String val1, String val2) {
        if (!StringUtils.equals(val1, val2)) {
            message();
        }
    }


    /**
     * 集合不为空时
     *
     * @param coll
     * @param message
     */
    public static void isNotEmpty(Collection coll, String message) {
        if (CollectionUtils.isNotEmpty(coll)) {
            message(message);
        }
    }

    /**
     * 集合不为空时
     *
     * @param coll
     * @param codeEnum
     */
    public static void isNotEmpty(Collection coll, CustomResultCodeEnum codeEnum) {
        if (CollectionUtils.isNotEmpty(coll)) {
            message(codeEnum);
        }
    }

    /**
     * 集合不为空时
     *
     * @param coll
     */
    public static void isNotEmpty(Collection coll) {
        if (CollectionUtils.isNotEmpty(coll)) {
            message();
        }
    }

    /**
     * 集合为空时
     *
     * @param coll
     * @param message
     */
    public static void isEmpty(Collection coll, String message) {
        if (CollectionUtils.isEmpty(coll)) {
            message(message);
        }
    }

    /**
     * 集合为空时
     *
     * @param coll
     * @param codeEnum
     */
    public static void isEmpty(Collection coll, CustomResultCodeEnum codeEnum) {
        if (CollectionUtils.isEmpty(coll)) {
            message(codeEnum);
        }
    }

    /**
     * 集合为空时
     *
     * @param coll
     */
    public static void isEmpty(Collection coll) {
        if (CollectionUtils.isEmpty(coll)) {
            message();
        }
    }


    /**
     * 字符串为空
     *
     * @param str
     * @param message
     */
    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            message(message);
        }
    }

    /**
     * 字符串为空
     *
     * @param str
     * @param codeEnum
     */
    public static void isBlank(String str, CustomResultCodeEnum codeEnum) {
        if (StringUtils.isBlank(str)) {
            message(codeEnum);
        }
    }

    /**
     * 字符串为空
     *
     * @param str
     */
    public static void isBlank(String str) {
        if (StringUtils.isBlank(str)) {
            message();
        }
    }

    /**
     * 字符串不为空
     *
     * @param str
     * @param message
     */
    public static void isNotBlank(String str, String message) {
        if (StringUtils.isNotBlank(str)) {
            message(message);
        }
    }

    /**
     * 字符串不为空
     *
     * @param str
     * @param codeEnum
     */
    public static void isNotBlank(String str, CustomResultCodeEnum codeEnum) {
        if (StringUtils.isNotBlank(str)) {
            message(codeEnum);
        }
    }

    /**
     * 字符串不为空
     *
     * @param str
     */
    public static void isNotBlank(String str) {
        if (StringUtils.isNotBlank(str)) {
            message();
        }
    }

    /**
     * 判断为真时
     *
     * @param judgment
     * @param message
     * @
     */
    public static void isTrue(boolean judgment, String message) {
        if (judgment) {
            message(message);
        }
    }

    /**
     * 判断为真时
     *
     * @param judgment
     * @
     */
    public static void isTrue(boolean judgment) {
        if (judgment) {
            message();
        }
    }

    /**
     * 判断为真时
     *
     * @param judgment
     * @param codeEnum
     * @
     */
    public static void isTrue(boolean judgment, CustomResultCodeEnum codeEnum) {
        if (judgment) {
            message(codeEnum);
        }
    }

    /**
     * 判断为假时
     *
     * @param judgment
     * @param message
     * @
     */
    public static void isFalse(boolean judgment, String message) {
        isTrue(!judgment, message);
    }

    /**
     * 判断为假时
     *
     * @param judgment
     * @param codeEnum
     * @
     */
    public static void isFalse(boolean judgment, CustomResultCodeEnum codeEnum) {
        isTrue(!judgment, codeEnum);
    }

    /**
     * 判断为假时
     *
     * @param judgment
     * @
     */
    public static void isFalse(boolean judgment) {
        isTrue(!judgment);
    }

    /**
     * map中包含key时
     *
     * @param map
     * @param key
     * @param message
     * @param <K>
     * @param <V>
     */
    public static <K, V> void containsKey(Map<K, V> map, K key, String message) {
        if (MapUtils.isNotEmpty(map) && map.containsKey(key)) {
            message(message);
        }
    }

    /**
     * map中包含key时
     *
     * @param map
     * @param key
     * @param codeEnum
     * @param <K>
     * @param <V>
     */
    public static <K, V> void containsKey(Map<K, V> map, K key, CustomResultCodeEnum codeEnum) {
        if (MapUtils.isNotEmpty(map) && map.containsKey(key)) {
            message(codeEnum);
        }
    }

    /**
     * map中包含key时
     *
     * @param map
     * @param key
     * @param <K>
     * @param <V>
     */
    public static <K, V> void containsKey(Map<K, V> map, K key) {
        if (MapUtils.isNotEmpty(map) && map.containsKey(key)) {
            message();
        }
    }

    /**
     * map中不包含key时
     *
     * @param map
     * @param key
     * @param message
     * @param <K>
     * @param <V>
     */
    public static <K, V> void notContainsKey(Map<K, V> map, K key, String message) {
        if (MapUtils.isEmpty(map) || !map.containsKey(key)) {
            message(message);
        }
    }

    /**
     * map中不包含key时
     *
     * @param map
     * @param key
     * @param codeEnum
     * @param <K>
     * @param <V>
     */
    public static <K, V> void notContainsKey(Map<K, V> map, K key, CustomResultCodeEnum codeEnum) {
        if (MapUtils.isEmpty(map) || !map.containsKey(key)) {
            message(codeEnum);
        }
    }

    /**
     * map中不包含key时
     *
     * @param map
     * @param key
     * @param <K>
     * @param <V>
     */
    public static <K, V> void notContainsKey(Map<K, V> map, K key) {
        if (MapUtils.isEmpty(map) || !map.containsKey(key)) {
            message();
        }
    }

    /**
     * 集合中包含obj时
     *
     * @param coll
     * @param obj
     * @param message
     * @param <T>
     * @
     */
    public static <T> void contains(Collection<T> coll, T obj, String message) {
        if (coll.contains(obj)) {
            message(message);
        }
    }

    /**
     * 集合中包含obj时
     *
     * @param coll
     * @param obj
     * @param codeEnum
     * @param <T>
     * @
     */
    public static <T> void contains(Collection<T> coll, T obj, CustomResultCodeEnum codeEnum) {
        if (coll.contains(obj)) {
            message(codeEnum);
        }
    }

    /**
     * 集合中包含obj时
     *
     * @param coll
     * @param obj
     * @param <T>
     * @
     */
    public static <T> void contains(Collection<T> coll, T obj) {
        if (coll.contains(obj)) {
            message();
        }
    }

    /**
     * 集合中不包含obj时
     *
     * @param coll
     * @param obj
     * @param message
     * @param <T>
     * @
     */
    public static <T> void notContains(Collection<T> coll, T obj, String message) {
        if (!coll.contains(obj)) {
            message(message);
        }
    }

    /**
     * 集合中不包含obj时
     *
     * @param coll
     * @param obj
     * @param codeEnum
     * @param <T>
     * @
     */
    public static <T> void notContains(Collection<T> coll, T obj, CustomResultCodeEnum codeEnum) {
        if (!coll.contains(obj)) {
            message(codeEnum);
        }
    }

    /**
     * 集合中不包含obj时
     *
     * @param coll
     * @param obj
     * @param <T>
     * @
     */
    public static <T> void notContains(Collection<T> coll, T obj) {
        if (!coll.contains(obj)) {
            message();
        }
    }

    /**
     * 默认数据异常
     */
    public static void message() {
        message(CustomResultCodeEnum.DATA_ERROR);
    }

    /**
     * 抛出业务异常
     *
     * @param message
     */
    public static void message(String message) {
        throw new CustomBusinessException(message);
    }

    /**
     * 抛出业务异常
     *
     * @param codeEnum
     */
    public static void message(CustomResultCodeEnum codeEnum) {
        throw new CustomBusinessException(codeEnum);
    }

    /**
     * 抛出自定义异常
     *
     * @param code
     * @param message
     */
    public static void message(int code, String message) {
        throw new CustomBusinessException(message, code);
    }


}

