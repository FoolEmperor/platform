package pers.lance.platform.base.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * 公共查询参数实体
 *
 * @author lance
 * @date 2018-05-06.
 */
@Data
public class CommonQueryParams implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int DEFAULT_PAGE = 1;
    public static final int DEFAULT_ROWS = 10;
    public static final int MAX_ROWS = 10000;

    private Integer page;
    private Integer rows;

    /**
     * 初始化
     */
    public void init() {
        if (Objects.isNull(page)) {
            page = DEFAULT_PAGE;
        }
        if (Objects.isNull(rows)) {
            rows = DEFAULT_ROWS;
        }
    }

    /**
     * 最大初始化
     */
    public void intiMaxRwos() {
        if (Objects.isNull(page)) {
            this.page = DEFAULT_PAGE;
        }
        if (Objects.isNull(rows)) {
            this.rows = MAX_ROWS;
        }
    }
}
