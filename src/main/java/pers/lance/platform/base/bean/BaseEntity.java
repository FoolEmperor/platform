package pers.lance.platform.base.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * id entity
 *
 * @author lance
 * @date 2018-05-06
 */
@MappedSuperclass
@Data
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "pers.lance.platform.base.util.CustomIdentifierGenerator")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idGenerator")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

}

