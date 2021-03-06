package pers.lance.platform.bean.entity;

import pers.lance.platform.base.bean.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * shiro 角色表
 *
 * @author lance
 * @date 2018-05-06
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class ShiroRole extends BaseEntity {

    @Column(columnDefinition = "varchar(255) default '' comment '角色名称'")
    private String name;

    @Column(unique = true, columnDefinition = "varchar(50) default '' comment '角色代码'")
    private String code;

    @JoinTable(name = "r_role_permission",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"role_id", "permission_id"})})
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<ShiroPermission> permissionList;

}
