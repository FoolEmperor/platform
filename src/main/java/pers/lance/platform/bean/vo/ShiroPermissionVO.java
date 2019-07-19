package pers.lance.platform.bean.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 权限许可 VO
 * @author: lance
 * @create: 2018-11-16 14:57
 */
@Data
public class ShiroPermissionVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String code;

    private String type;

}
