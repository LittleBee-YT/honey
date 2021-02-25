package com.lbee.common.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lbee.common.data.base.BaseEntity;
import com.lbee.common.data.base.TenantEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* 操作日志错误记录表
*
* @author Little bee
* @date 2020-11-27 16:18:11
*/
@Data
@TableName("sys_operation_error_log")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "操作日志错误记录表")
//public class OperationErrorLog extends TenantEntity {
public class OperationErrorLog extends BaseEntity {
private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "功能模块")
    private String opModule;

    @ApiModelProperty(value = "操作类型")
    private String opType;

    @ApiModelProperty(value = "操作描述")
    private String opDesc;

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


}

