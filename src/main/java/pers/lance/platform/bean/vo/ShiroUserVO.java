package pers.lance.platform.bean.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * shiro 用户 VO
 *
 * @author lance
 * @date 2019-07-25T16:03:54.821
 */
@ApiModel("shiro 用户 VO")
@Data
public class ShiroUserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "标识", dataType = "String", example = "string")
    private String id;

    @ApiModelProperty(name = "name", value = "用户真实姓名", dataType = "String", example = "string")
    private String name;

    @ApiModelProperty(name = "username", value = "用户名", dataType = "String", example = "string")
    private String username;

    @ApiModelProperty(name = "password", value = "用户密码", dataType = "String", example = "string")
    private String password;

    @ApiModelProperty(name = "phoneNumber", value = "手机号码", dataType = "String", example = "string")
    private String phoneNumber;

    @ApiModelProperty(name = "enable", value = "是否启用：1.启用;0.未启用", dataType = "Boolean", example = "false")
    private Boolean enable;

    @ApiModelProperty(name = "Date", value = "名称", dataType = "String", example = "string")
    private String registTime;

    @ApiModelProperty(name = "roleVOList", value = "角色列表", dataType = "[Lcom.szl.protocoltransform.protocol.bean.vo.ShiroRoleVO")
    private List<ShiroRoleVO> roleVOList;
}
