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
* 系统用户详情扩展
*
* @author Little bee
* @date 2020-11-20 10:38:16
*/
@Data
@TableName("sys_user_detail")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "系统用户详情扩展")
public class UserDetail extends TenantEntity {
private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private Long userId;

    @ApiModelProperty(value = "详情")
    private String info;


}

