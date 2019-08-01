package pers.lance.platform.bean.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import pers.lance.platform.base.bean.group.Save;
import pers.lance.platform.base.bean.group.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * shiro 权限许可 DTO
 *
 * @author lance
 * @date 2019-07-25T10:46:38.217
 */
@ApiModel("权限许可 DTO")
@Data
public class ShiroPermissionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", name = "id", dataType = "Long")
    @Null(message = "id需要为空", groups = {Save.class})
    @NotNull(message = "id不能为空", groups = {Update.class})
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(name = "name", value = "权限名称", dataType = "String", example = "string")
    @NotBlank(message = "权限名称不能为空", groups = {Save.class, Update.class})
    private String name;

    @ApiModelProperty(name = "code", value = "权限代码", dataType = "String", example = "string")
    private String code;

    @ApiModelProperty(name = "type", value = "权限类型", dataType = "String", example = "string")
    private String type;

}
