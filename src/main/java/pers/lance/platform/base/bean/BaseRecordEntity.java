package pers.lance.platform.base.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * 操作记录
 *
 * @author lance
 * @date 2018-05-06
 */
@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseRecordEntity extends BaseEntity {

    @CreatedBy()
    @Column(columnDefinition = "bigint comment '创建人id'")
    private Long createBy;

    @CreatedBy()
    @Column(columnDefinition = "varchar(255) default '' comment '创建人'")
    private String createName;

    @CreatedDate
    @Column(columnDefinition = "datetime comment '创建时间'")
    private Date createDate;

    @LastModifiedBy()
    @Column(columnDefinition = "bigint comment '最近更新人id'")
    private Long updateBy;

    @LastModifiedBy()
    @Column(columnDefinition = "varchar(255) default '' comment '最近更新人'")
    private String updateName;

    @LastModifiedDate
    @Column(columnDefinition = "datetime comment '最近更新时间'")
    private Date updateDate;

}
