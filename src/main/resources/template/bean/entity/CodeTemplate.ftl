package pers.lance.platform.bean.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import pers.lance.platform.base.bean.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * ${module} Entity
 *
 * @author ${author}
 * @date ${date}
 */
@Entity
@Data
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(callSuper = true)
public class CodeTemplate extends BaseEntity {
    @Column(columnDefinition = "varchar(255) default '' comment '名称'")
    private String name;

    @Column(columnDefinition = "varchar(255) default '' comment '类型'")
    private String type;
}
