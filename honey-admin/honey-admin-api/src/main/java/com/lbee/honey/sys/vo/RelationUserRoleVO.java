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
* 系统用户角色关联 VO
*
* @author Little bee
* @date 2020-11-19 12:33:03
*/
@Data
@ApiModel(description = "系统用户角色关联")
public class RelationUserRoleVO extends BaseVO {
private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户Id")
    private Long userId;
    @ApiModelProperty(value = "角色Id")
    private Long roleId;
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

