package pers.lance.platform.bean.entity;

import pers.lance.platform.base.bean.BaseRecordEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * demo
 *
 * @author lance
 * @date 2018-05-06
 */
@Entity
@Data
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(callSuper = true)
public class Demo extends BaseRecordEntity {
    @Column(columnDefinition = "varchar(255) default '' comment '名称'")
    private String name;

    @Column(columnDefinition = "varchar(255) default '' comment '类型'")
    private String type;

    @Column(columnDefinition = "varchar(255) default '' comment '等级'")
    private String level;

}
