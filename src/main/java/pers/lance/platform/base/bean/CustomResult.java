package pers.lance.platform.base.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 统一返回包装类
 *
 * @param <T>
 * @author su
 * @data 2018-11-07
 */
@Data
@ApiModel("Custom Result")
public class CustomResult<T> {

    @ApiModelProperty(value = "成功标识", name = "success", dataType = "Boolean", example = "true")
    private Boolean success;

    @ApiModelProperty(value = "状态代码", name = "code", dataType = "Integer", example = "0")
    private Integer code;

    @ApiModelProperty(value = "时间戳", name = "timestamp", dataType = "Long")
    private Long timestamp;

    @ApiModelProperty(value = "返回信息", name = "message", dataType = "String")
    private String message;

    @ApiModelProperty(value = "返回数据", name = "data", dataType = "Object")
    private T data;
}
