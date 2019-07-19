package pers.lance.platform.base.util;


import pers.lance.platform.base.bean.CustomSymbol;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 实体验证工具类
 *
 * @author lance
 * @date 2018-05-19 15:38
 */
public class ValidatorUtils {

    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    /**
     * 验证并且在不通过时抛出异常消息
     *
     * @param t
     * @param groups
     * @param <T>
     */
    public static <T> void validate(T t, Class... groups) {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> validateResult = validator.validate(t, groups);
        String message = generalMethod(validateResult);
        ErrorUtils.isTrue(StringUtils.isNotBlank(message), message);
    }

    /**
     * Collection 验证并且在不通过时抛出异常消息
     *
     * @param collection
     * @param groups
     * @param <T>
     */
    public static <T> void validateCollection(Collection<T> collection, Class... groups) {
        StringBuffer message = new StringBuffer();
        Validator validator = validatorFactory.getValidator();
        collection.forEach(e -> {
            Set<ConstraintViolation<T>> validateResult = validator.validate(e, groups);
            if (!validateResult.isEmpty()) {
                message.append(generalMethod(validateResult));
            }
        });
        ErrorUtils.isTrue(StringUtils.isNotBlank(message.toString()), message.toString());
    }

    /**
     * 通用validateResult处理方法
     *
     * @param validateResult
     * @param <T>
     * @return
     */
    private static <T> String generalMethod(Set<ConstraintViolation<T>> validateResult) {
        if (!validateResult.isEmpty()) {
            List<String> errorList = new ArrayList<String>();
            validateResult.forEach(e -> {
                errorList.add(e.getPropertyPath().toString() + CustomSymbol.COLON + e.getMessage());
            });
            return StringUtils.join(errorList, CustomSymbol.COMMA) + CustomSymbol.PERIOD;
        }
        return null;
    }

    /**
     * 获取验证结果set
     *
     * @param e
     * @param groups
     * @param <T>
     */
    public static <T> Set<ConstraintViolation<T>> getValidateResult(T e, Class... groups) {
        Validator validator = validatorFactory.getValidator();
        return validator.validate(e, groups);
    }

    /**
     * 获取 Validator
     *
     * @param <T>
     */
    public static <T> Validator getValidator() {
        return validatorFactory.getValidator();
    }


    /**
     * 解析 BindingResult
     *
     * @param bindingResult
     * @return
     */
    public static String parseBindingResult(BindingResult bindingResult) {
        List<String> errorList = new ArrayList<String>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorList.add(fieldError.getField() + CustomSymbol.COLON + fieldError.getDefaultMessage());
        }
        return StringUtils.join(errorList, CustomSymbol.COMMA) + CustomSymbol.PERIOD;
    }

}
