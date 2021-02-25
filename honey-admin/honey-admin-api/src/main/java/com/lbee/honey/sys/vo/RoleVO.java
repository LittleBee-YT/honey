package com.lbee.honey.sys.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lbee.common.data.base.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
* 系统用户角色 VO
*
* @author Little bee
* @date 2020-11-19 12:33:03
*/
@Data
@ApiModel(description = "系统用户角色")
public class RoleVO extends BaseVO {
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
    @ApiModelProperty(value = "租户Id")
    private Long tenantId;
    @ApiModelProperty(value = "删除标记")
    private Integer delFlag;
    @ApiModelProperty(value = "创建者")
    private Long createBy;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;
    @ApiModelProperty(value = "更新者")
    private Long updateBy;
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateDate;
    @ApiModelProperty(value = "备注信息")
    private String remarks;

}

