package pers.lance.platform.base.util;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 自定义公共工具类
 *
 * @author lance
 * @date 2018-05-06
 */
public class CustomCommonUtils {

    private static final String CLASS = "class";

    /**
     *
     *
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames(Object source) {
        BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 非空字段复制
     *
     * @param source
     * @param target
     */
    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }


    /**
     * bean转map
     * bean字段名=>map.key
     * bean字段值=>map.value
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public static Map<String, Object> beanToMap(Object obj) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
        PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
        int size = (descriptors.length / 3 + 1) * 4;
        Map<String, Object> params = new HashMap<String, Object>(size);
        for (int i = 0; i < descriptors.length; i++) {
            String name = descriptors[i].getName();
            if (!CLASS.equals(name)) {
                params.put(name, propertyUtilsBean.getNestedProperty(obj, name));
            }
        }
        return params;
    }


}
