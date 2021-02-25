package com.lbee.common.data.base;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@ApiModel(description = "Tree基础字段")
public class TreeEntity<T> extends BaseEntity<T> {

    @ApiModelProperty(value = "父级编号")
    protected Long parentId;

    @ApiModelProperty(value = "所有父级编号")
    protected String parentIds;

    @ApiModelProperty(value = "名称")
    protected String name;

    @ApiModelProperty(value = "排序")
    protected Integer sort;

    /**
     * 自定义SQL（SQL标识，SQL内容）
     */
    @TableField(exist = false)
    protected List<T> children;

}
