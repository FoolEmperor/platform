package pers.lance.platform.bean.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 权限许可 VO
 *
 * @author: lance
 * @create: 2018-11-16 14:57
 */
@Data
@ApiModel("权限许可 VO")
public class ShiroPermissionVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "name", value = "权限名称", dataType = "String", example = "string")
    private String name;

    @ApiModelProperty(name = "code", value = "权限代码", dataType = "String", example = "string")
    private String code;

    @ApiModelProperty(name = "type", value = "权限类型", dataType = "String", example = "string")
    private String type;

}
