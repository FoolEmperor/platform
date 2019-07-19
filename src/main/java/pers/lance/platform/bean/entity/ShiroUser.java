package pers.lance.platform.bean.entity;

import pers.lance.platform.base.bean.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;
import java.util.Date;
import java.util.List;

/**
 * shiro 用户表
 *
 * @author lance
 * @date 2018-05-06
 */
@Entity
@Data
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class ShiroUser extends BaseEntity {

    @Column(unique = true, columnDefinition = "varchar(50) comment '用户名'")
    private String username;

    @Column(columnDefinition = "varchar(255) comment '用户真实姓名'")
    private String name;

    @Column(columnDefinition = "varchar(255) comment '用户密码'")
    private String password;

    @Column(columnDefinition = "varchar(255) comment '手机号码'")
    private String phoneNumber;

    @Column(columnDefinition = "bit(1) default 1 comment '是有有效：1.有效;0.无效'")
    private Boolean enable;

    @CreatedDate
    @Column(columnDefinition = "DATETIME comment '注册时间'")
    private Date registTime;

    @JoinTable(name = "r_user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})})
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ShiroRole> roles;
}
