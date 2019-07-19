package pers.lance.platform.bean.query;

import pers.lance.platform.base.bean.CommonQueryParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * demo query params
 *
 * @author lance
 * @date 2018-05-06
 */
@Data
@ApiModel("Demo Query")
@EqualsAndHashCode(callSuper = true)
public class DemoQuery extends CommonQueryParams {

    @ApiModelProperty(name = "name", value = "名称", dataType = "String", example = "张三")
    private String name;

}
