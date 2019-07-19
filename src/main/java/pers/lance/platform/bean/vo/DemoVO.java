package pers.lance.platform.bean.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * demo vo
 *
 * @author lance
 * @date 2018-05-06
 */
@ApiModel("Demo VO")
@Data
public class DemoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", name = "id", dataType = "String", example = "1234567890")
    private String id;

    @ApiModelProperty(name = "name", value = "名称", dataType = "String", example = "张三")
    private String name;

    @ApiModelProperty(name = "type", value = "类型", dataType = "String", example = "man")
    private String type;

    @ApiModelProperty(name = "level", value = "等级", dataType = "String", example = "1")
    private String level;
}
