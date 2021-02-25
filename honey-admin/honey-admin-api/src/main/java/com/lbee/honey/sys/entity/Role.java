package com.lbee.honey.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lbee.common.data.base.TenantEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
* 系统用户角色
*
* @author Little bee
* @date 2020-11-19 12:33:03
*/
@Data
@TableName("sys_role")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "系统用户角色")
public class Role extends TenantEntity {
private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色标示")
    private String key;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "隐藏")
    private Integer hide;

    @ApiModelProperty(value = "角色类型")
    private Integer type;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "状态(1:启用,2:禁用)")
    private Integer status;


}

