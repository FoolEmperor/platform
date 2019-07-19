package pers.lance.platform.bean.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色VO
 *
 * @author: lance
 * @create: 2018-11-16 14:55
 */
@Data
public class ShiroRoleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String code;

    private List<ShiroPermissionVO> permissions;
}
