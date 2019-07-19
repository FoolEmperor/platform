package pers.lance.platform.bean.dto;


import pers.lance.platform.base.bean.group.Save;
import pers.lance.platform.base.bean.group.Update;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * demo DTO
 *
 * @author lance
 * @date 2018-05-06
 */
@ApiModel("Demo DTO")
@Data
public class DemoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", name = "id", dataType = "Long")
    @Null(message = "id需要为空", groups = {Save.class})
    @NotNull(message = "id不能为空", groups = {Update.class})
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(name = "name", value = "名称", dataType = "String", example = "张三")
    @NotBlank(message = "name不能为空", groups = {Save.class, Update.class})
    private String name;

    @ApiModelProperty(name = "type", value = "类型", dataType = "String", example = "man")
    private String type;

    @ApiModelProperty(name = "level", value = "等级", dataType = "String", example = "1")
    private String level;
}
