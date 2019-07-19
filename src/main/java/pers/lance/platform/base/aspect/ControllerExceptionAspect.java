package pers.lance.platform.base.aspect;

import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import pers.lance.platform.base.bean.CustomBusinessException;
import pers.lance.platform.base.bean.CustomResult;
import pers.lance.platform.base.bean.CustomResultCodeEnum;
import pers.lance.platform.base.util.CustomResultUtils;
import pers.lance.platform.base.util.ValidatorUtils;


/**
 * 统一异常处理
 *
 * @author lance
 * @date 2018-05-06
 */
@RestControllerAdvice
public class ControllerExceptionAspect {

    /**
     * RestDescribeException 异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = CustomBusinessException.class)
    public CustomResult doException(CustomBusinessException e) {
        e.printStackTrace();
        return CustomResultUtils.error(e.getCode(), e.getMessage());
    }

    /**
     * BindException 异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public CustomResult handleBindException(BindException e) {
        e.printStackTrace();
        return CustomResultUtils.error(CustomResultCodeEnum.FIELD_VALIDTION_ERROR.getCode(), ValidatorUtils.parseBindingResult(e.getBindingResult()));
    }


    /**
     * MethodArgumentNotValidException 异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CustomResult handleBindException(MethodArgumentNotValidException e) {
        e.printStackTrace();
        return CustomResultUtils.error(CustomResultCodeEnum.FIELD_VALIDTION_ERROR.getCode(), ValidatorUtils.parseBindingResult(e.getBindingResult()));
    }

    /**
     * UnauthenticatedException 异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public CustomResult handleBindException(UnauthenticatedException e) {
        e.printStackTrace();
        return CustomResultUtils.error(CustomResultCodeEnum.UNAUTHENTICATED);
    }

    /**
     * UnauthorizedException 异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    public CustomResult handleBindException(UnauthorizedException e) {
        e.printStackTrace();
        return CustomResultUtils.error(CustomResultCodeEnum.UNAUTHORIZED);
    }

    /**
     * UnknownAccountException 异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(UnknownAccountException.class)
    public CustomResult handleBindException(UnknownAccountException e) {
        e.printStackTrace();
        return CustomResultUtils.error(CustomResultCodeEnum.UNKNOWN_ACCOUNT);
    }

    /**
     * IncorrectCredentialsException 异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IncorrectCredentialsException.class)
    public CustomResult handleBindException(IncorrectCredentialsException e) {
        e.printStackTrace();
        return CustomResultUtils.error(CustomResultCodeEnum.INCORRECT_CREDENTIALS);
    }


    /**
     * NoHandlerFoundException 异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public CustomResult doException(NoHandlerFoundException e) {
        e.printStackTrace();
        return CustomResultUtils.error(CustomResultCodeEnum.NO_HANDLER_FOUND);
    }

    /**
     * HttpRequestMethodNotSupportedException 异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public CustomResult doException(HttpRequestMethodNotSupportedException e) {
        e.printStackTrace();
        return CustomResultUtils.error(CustomResultCodeEnum.NO_HANDLER_FOUND);
    }

    /**
     * DisabledAccountException 异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = DisabledAccountException.class)
    public CustomResult doException(DisabledAccountException e) {
        e.printStackTrace();
        return CustomResultUtils.error(CustomResultCodeEnum.DISABLED_ACCOUNT);
    }

    /**
     * RuntimeException 异常处理
     *
     * @param e
     * @return
     */

    @ExceptionHandler(value = RuntimeException.class)
    public CustomResult doException(RuntimeException e) {
        e.printStackTrace();
        return CustomResultUtils.error(CustomResultCodeEnum.DATA_ERROR);
    }

    /**
     * Exception 异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public CustomResult doException(Exception e) {
        e.printStackTrace();
        return CustomResultUtils.error(CustomResultCodeEnum.UNKNOW_ERROR);
    }

}
