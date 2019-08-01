package pers.lance.platform.bean.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.lance.platform.base.bean.group.Save;
import pers.lance.platform.base.bean.group.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * ${module} DTO
 *
 * @author ${author}
 * @date ${date}
 */
@ApiModel("${module} DTO")
@Data
public class CodeTemplateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", name = "id", dataType = "java.lang.String")
    @Null(message = "id需要为空", groups = {Save.class})
    @NotNull(message = "id不能为空", groups = {Update.class})
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(name = "name", value = "名称", dataType = "java.lang.String", example = "string")
    @NotBlank(message = "name不能为空", groups = {Save.class, Update.class})
    private String name;

    @ApiModelProperty(name = "type", value = "类型", dataType = "java.lang.String", example = "string")
    private String type;

}
