package pers.lance.platform.base.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 统一返回异常
 *
 * @author lance
 * @date 2018-05-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomBusinessException extends RuntimeException {
    private Integer code;

    /**
     * 继承exception，加入错误状态值
     *
     * @param codeEnum
     */
    public CustomBusinessException(CustomResultCodeEnum codeEnum) {
        super(codeEnum.getMessage());
        this.code = codeEnum.getCode();
    }

    /**
     * 自定义错误信息
     *
     * @param message
     * @param code
     */
    public CustomBusinessException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    /**
     * 业务异常
     *
     * @param message
     */
    public CustomBusinessException(String message) {
        super(message);
        this.code = CustomResultCodeEnum.BUSINESS_ERROR.getCode();

    }
}
