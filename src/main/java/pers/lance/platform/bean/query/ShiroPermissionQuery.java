package pers.lance.platform.bean.query;

import pers.lance.platform.base.bean.CommonQueryParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * shiro 权限许可 Query
 *
 * @author lance
 * @date 2019-07-25T10:46:38.217
 */
@Data
@ApiModel("shiro 权限许可 Query")
@EqualsAndHashCode(callSuper = true)
public class ShiroPermissionQuery extends CommonQueryParams {

    @ApiModelProperty(name = "name", value = "权限名称", dataType = "String", example = "string")
    private String name;

    @ApiModelProperty(name = "code", value = "权限代码", dataType = "String", example = "string")
    private String code;

    @ApiModelProperty(name = "type", value = "权限类型", dataType = "String", example = "string")
    private String type;

}
