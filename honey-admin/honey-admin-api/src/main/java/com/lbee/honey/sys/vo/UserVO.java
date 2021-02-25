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
* 系统用户 VO
*
* @author Little bee
* @date 2020-11-17 18:05:33
*/
@Data
@ApiModel(description = "系统用户")
public class UserVO extends BaseVO {
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

