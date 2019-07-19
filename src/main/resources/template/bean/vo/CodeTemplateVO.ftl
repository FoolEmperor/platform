package pers.lance.platform.bean.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * ${module} VO
 *
 * @author ${author}
 * @date ${date}
 */
@ApiModel("${module} VO")
@Data
public class CodeTemplateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", name = "id", dataType = "Long")
    private String id;

    @ApiModelProperty(name = "name", value = "名称", dataType = "String", example = "string")
    private String name;

    @ApiModelProperty(name = "type", value = "类型", dataType = "String", example = "string")
    private String type;

}
