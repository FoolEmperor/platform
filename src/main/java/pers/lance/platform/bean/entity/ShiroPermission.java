package pers.lance.platform.bean.entity;

import pers.lance.platform.base.bean.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * shiro 权限许可表
 *
 * @author lance
 * @date 2018-05-06
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class ShiroPermission extends BaseEntity {

    @Column(columnDefinition = "varchar(255) comment '权限名称'")
    private String name;

    @Column(unique = true, columnDefinition = "varchar(50) comment '权限代码'")
    private String code;

    @Column(columnDefinition = "varchar(255) comment '权限类型'")
    private String type;

}
