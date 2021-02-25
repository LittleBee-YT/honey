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
* 系统用户角色关联
*
* @author Little bee
* @date 2020-11-19 12:33:03
*/
@Data
@TableName("sys_relation_user_role")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "系统用户角色关联")
public class RelationUserRole extends TenantEntity {
private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户Id")
    private Long userId;

    @ApiModelProperty(value = "角色Id")
    private Long roleId;


}

