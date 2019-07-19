package pers.lance.platform.bean.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录信息
 *
 * @author: lance
 * @create: 2018-11-16 10:52
 */
@Data
@ApiModel("用户登录信息")
public class UserLoginVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "用户ID", dataType = "String")
    private String id;

    @ApiModelProperty(name = "username", value = "用户名", dataType = "String")
    private String username;

    @ApiModelProperty(name = "name", value = "名称", dataType = "String")
    private String name;

    @ApiModelProperty(name = "code", value = "角色编码", dataType = "String")
    private String roleCode;

    @ApiModelProperty(name = "codeName", value = "角色名称", dataType = "String")
    private String roleName;

}
