package pers.lance.platform.bean.query;

import pers.lance.platform.base.bean.CommonQueryParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色 Query
 *
 * @author lance
 * @date 2019-07-25T11:04:34.531
 */
@Data
@ApiModel("角色 Query")
@EqualsAndHashCode(callSuper = true)
public class ShiroRoleQuery extends CommonQueryParams {

    @ApiModelProperty(name = "name", value = "名称", dataType = "String", example = "string")
    private String name;

}
