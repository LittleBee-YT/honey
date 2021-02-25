package com.lbee.common.data.base;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础实体类
 *
 * @author lbee
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "基础字段")
public class BaseEntity<T> implements Serializable {

    /**
     * 主键ID
     */
    protected Long id;

    @ApiModelProperty(value = "创建者")
    protected Long createBy;

    @ApiModelProperty(value = "创建时间")
    protected LocalDateTime createDate;

    @ApiModelProperty(value = "更新者")
    protected Long updateBy;

    @ApiModelProperty(value = "更新时间")
    protected LocalDateTime updateDate;

    @ApiModelProperty(value = "备注信息")
    protected String remarks;

    @ApiModelProperty(value = "删除标记")
    @TableLogic
    protected Integer delFlag;

    /**
     * 自定义SQL（SQL标识，SQL内容）
     */
//    @TableField(exist = false)
//    protected transient Map<String, String> sqlMap = Maps.newHashMap();

    /**
     * 删除标记（0：正常；1：删除；）
     */
    public static final Integer DEL_FLAG_NORMAL = 0;
    public static final Integer DEL_FLAG_DELETE = 1;
}
