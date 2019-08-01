package pers.lance.platform.bean.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.lance.platform.base.bean.CommonQueryParams;

/**
 * ${module} Query
 *
 * @author ${author}
 * @date ${date}
 */
@Data
@ApiModel("${module} Query")
@EqualsAndHashCode(callSuper = true)
public class CodeTemplateQuery extends CommonQueryParams {

    @ApiModelProperty(name = "name", value = "名称", dataType = "java.lang.String", example = "string")
    private String name;

}
