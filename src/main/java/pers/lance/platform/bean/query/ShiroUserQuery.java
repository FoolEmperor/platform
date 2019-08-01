package pers.lance.platform.bean.query;

import pers.lance.platform.base.bean.CommonQueryParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * shiro 用户 Query
 *
 * @author lance
 * @date 2019-07-25T16:03:54.821
 */
@Data
@ApiModel("shiro 用户 Query")
@EqualsAndHashCode(callSuper = true)
public class ShiroUserQuery extends CommonQueryParams {

    @ApiModelProperty(name = "name", value = "名称", dataType = "String", example = "string")
    private String name;

}
