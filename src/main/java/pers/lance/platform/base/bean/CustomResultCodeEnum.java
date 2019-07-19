package pers.lance.platform.base.bean;

/**
 * 常用返回code枚举
 *
 * @author lance
 * @date 2018-05-06
 */
public enum CustomResultCodeEnum {
    // success
    SUCCESS(0, "success"),
    // 404
    NO_HANDLER_FOUND(404, "404"),
    // 业务异常
    BUSINESS_ERROR(-100, "业务异常"),
    // 未登录
    UNLOGIN(-300, "未登录"),
    // 认证失效
    UNAUTHENTICATED(-140, "认证失效"),
    // 用户名或密码输入错误，请重新输入
    UNKNOWN_ACCOUNT(-150, "用户名或密码输入错误，请重新输入"),
    // 用户名或密码输入错误，请重新输入
    INCORRECT_CREDENTIALS(-160, "用户名或密码输入错误，请重新输入"),
    // 用户已禁用
    DISABLED_ACCOUNT(-170, "用户已禁用"),
    // 没有权限
    UNAUTHORIZED(-200,"没有权限"),
    // 数据异常
    DATA_ERROR(-600, "数据异常"),
    // 数据校验异常
    FIELD_VALIDTION_ERROR(-700, "数据校验异常"),
    // 切面异常
    ASPECT_ERROR(-900, "切面异常"),
    // 未知错误
    UNKNOW_ERROR(500, "未知错误");


    private Integer code;

    private String message;

    CustomResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
