package pers.lance.platform.base.util;


import pers.lance.platform.base.bean.CustomResult;
import pers.lance.platform.base.bean.CustomResultCodeEnum;

import java.util.Calendar;

/**
 * 常用返回code枚举
 *
 * @author lance
 * @date 2018-05-06
 */
public class CustomResultUtils {


    /**
     * 提供给部分不需要出參的接口
     *
     * @return
     */
    public static CustomResult success() {
        return success(null);
    }

    /**
     * 返回成功，传入返回体具体出參
     *
     * @param message
     * @param object
     * @return
     */
    public static <T> CustomResult success(String message, T object) {
        return generalMethod(CustomResultCodeEnum.SUCCESS.getCode(), message, object);
    }

    /**
     * 返回成功，传入返回体具体出參
     *
     * @param object
     * @return
     */
    public static <T> CustomResult success(T object) {
        return generalMethod(CustomResultCodeEnum.SUCCESS.getCode(), CustomResultCodeEnum.SUCCESS.getMessage(), object);
    }

    /**
     * 自定义错误信息
     *
     * @param message
     * @return
     */
    public static CustomResult error(String message) {
        return generalMethod(CustomResultCodeEnum.BUSINESS_ERROR.getCode(), message, null);
    }

    /**
     * 自定义错误信息
     *
     * @param message
     * @return
     */
    public static <T> CustomResult error(String message, T object) {
        return generalMethod(CustomResultCodeEnum.BUSINESS_ERROR.getCode(), message, object);
    }

    /**
     * 自定义错误信息
     *
     * @param code
     * @param message
     * @return
     */
    public static CustomResult error(Integer code, String message) {
        return generalMethod(code, message, null);
    }
    
    /**
     * 自定义错误信息及回传信息
     *
     * @param code
     * @param message
     * @param object
     * @param <T>
     * @return
     */
    public static <T> CustomResult error(Integer code, String message, T object) {
        return generalMethod(code, message, object);
    }

    /**
     * 返回异常信息，在已知的范围内
     *
     * @param codeEnum
     * @return
     */
    public static CustomResult error(CustomResultCodeEnum codeEnum) {
        return generalMethod(codeEnum.getCode(), codeEnum.getMessage(), null);
    }

    /**
     * 返回异常信息，在已知的范围内
     *
     * @param codeEnum
     * @return
     */
    public static <T> CustomResult error(CustomResultCodeEnum codeEnum, T object) {
        return generalMethod(codeEnum.getCode(), codeEnum.getMessage(), object);
    }

    /**
     * 通用方法
     *
     * @param code
     * @param message
     * @param object
     * @param <T>
     * @return
     */
    private static <T> CustomResult generalMethod(Integer code, String message, T object) {
        CustomResult<T> customResult = new CustomResult<T>();
        customResult.setSuccess(CustomResultCodeEnum.SUCCESS.getCode().equals(code));
        customResult.setCode(code);
        customResult.setTimestamp(Calendar.getInstance().getTimeInMillis());
        customResult.setMessage(message);
        customResult.setData(object);
        return customResult;
    }

}
