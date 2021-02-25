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
* 系统用户
*
* @author Little bee
* @date 2020-11-17 18:05:33
*/
@Data
@TableName("sys_user")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "系统用户")
public class User extends TenantEntity {
private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "手机")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "密码是否存在(0:Not,1:Yes)")
    private Integer passwordExist;

    @ApiModelProperty(value = "密码过期时间")
    private LocalDate pwdExpireAt;

    @ApiModelProperty(value = "是否启用(0:Not,1:Yes)")
    private Integer enabled;

    @ApiModelProperty(value = "冻结FLAG(0:Not,1:Yes)")
    private Integer locked;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String avatar;


}

