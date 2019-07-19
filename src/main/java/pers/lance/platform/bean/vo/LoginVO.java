package pers.lance.platform.bean.vo;


import pers.lance.platform.base.bean.CustomRegexp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Login VO
 *
 * @author lance
 * @date 2018-05-06
 */
@Data
@ApiModel("用户登录实体")
public class LoginVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "username", value = "用户名", required = true, dataType = "String", example = "demouser")
    @Pattern(regexp = CustomRegexp.USERNAME, message = CustomRegexp.USERNAME_MESSAGE)
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(name = "password", value = "密码", required = true, dataType = "String", example = "123422")
    @Pattern(regexp = CustomRegexp.PASSWORD, message = CustomRegexp.PASSWORD_MESSAGE)
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(name = "rememberMe", value = "记住我", dataType = "Boolean")
    private Boolean rememberMe;
}
