package com.lbee.common.core.vo;

import com.lbee.common.data.base.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
* 操作日志错误记录表 VO
*
* @author Little bee
* @date 2020-11-27 16:18:11
*/
@Data
@ApiModel(description = "操作日志错误记录表")
public class OperationErrorLogVO extends BaseVO {
private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "功能模块")
    private String module;
    @ApiModelProperty(value = "操作类型")
    private String type;
    @ApiModelProperty(value = "操作描述")
    private String desc;
    @ApiModelProperty(value = "请求参数")
    private String requestParam;
    @ApiModelProperty(value = "错误消息")
    private String errorMessage;
    @ApiModelProperty(value = "请求uri")
    private String uri;
    @ApiModelProperty(value = "操作方法")
    private String method;
    @ApiModelProperty(value = "操作人")
    private String opUser;
    @ApiModelProperty(value = "用户登陆ip")
    private String loginIp;
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

