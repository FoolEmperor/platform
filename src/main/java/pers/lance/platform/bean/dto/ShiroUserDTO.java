package pers.lance.platform.bean.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import pers.lance.platform.base.bean.group.Save;
import pers.lance.platform.base.bean.group.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * shiro 用户 DTO
 *
 * @author lance
 * @date 2019-07-25T16:03:54.821
 */
@ApiModel("shiro 用户 DTO")
@Data
public class ShiroUserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", name = "id", dataType = "Long")
    @Null(message = "id需要为空", groups = {Save.class})
    @NotNull(message = "id不能为空", groups = {Update.class})
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(name = "name", value = "用户真实姓名", dataType = "String", example = "string")
    @NotBlank(message = "name不能为空", groups = {Save.class, Update.class})
    private String name;

    @ApiModelProperty(name = "username", value = "用户名", dataType = "String", example = "string")
    private String username;

    @ApiModelProperty(name = "password", value = "用户密码", dataType = "String", example = "string")
    private String password;

    @ApiModelProperty(name = "phoneNumber", value = "手机号码", dataType = "String", example = "string")
    private String phoneNumber;

    @ApiModelProperty(name = "enable", value = "是否启用：true.启用;false.未启用", dataType = "Boolean", example = "false")
    private Boolean enable;

    @ApiModelProperty(name = "Date", value = "名称", dataType = "String", example = "string")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registTime;

    @ApiModelProperty(name = "roleIdList", value = "角色id列表", dataType = "[Ljava.lang.Long")
    private List<Long> roleIdList;

}
